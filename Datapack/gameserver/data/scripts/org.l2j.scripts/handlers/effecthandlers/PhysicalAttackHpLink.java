/*
 * This file is part of the L2J Mobius project.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package handlers.effecthandlers;

import org.l2j.gameserver.enums.ShotType;
import org.l2j.gameserver.model.StatsSet;
import org.l2j.gameserver.model.actor.Attackable;
import org.l2j.gameserver.model.actor.Creature;
import org.l2j.gameserver.model.effects.AbstractEffect;
import org.l2j.gameserver.model.effects.EffectType;
import org.l2j.gameserver.model.items.instance.Item;
import org.l2j.gameserver.model.skills.Skill;
import org.l2j.gameserver.model.stats.Formulas;
import org.l2j.gameserver.model.stats.Stats;

import static org.l2j.gameserver.util.GameUtils.isAttackable;

/**
 * Physical Attack HP Link effect implementation.<br>
 * <b>Note</b>: Initial formula taken from PhysicalAttack.
 * @author Adry_85, Nik
 */
public final class PhysicalAttackHpLink extends AbstractEffect
{
	private final double _power;
	private final double _criticalChance;
	private final boolean _overHit;
	
	public PhysicalAttackHpLink(StatsSet params)
	{
		_power = params.getDouble("power", 0);
		_criticalChance = params.getDouble("criticalChance", 0);
		_overHit = params.getBoolean("overHit", false);
	}
	
	@Override
	public boolean calcSuccess(Creature effector, Creature effected, Skill skill)
	{
		return !Formulas.calcPhysicalSkillEvasion(effector, effected, skill);
	}
	
	@Override
	public EffectType getEffectType()
	{
		return EffectType.PHYSICAL_ATTACK_HP_LINK;
	}
	
	@Override
	public boolean isInstant()
	{
		return true;
	}
	
	@Override
	public void instant(Creature effector, Creature effected, Skill skill, Item item)
	{
		if (effector.isAlikeDead())
		{
			return;
		}
		
		if (_overHit && isAttackable(effected))
		{
			((Attackable) effected).overhitEnabled(true);
		}
		
		final double attack = effector.getPAtk();
		double defence = effected.getPDef();
		
		switch (Formulas.calcShldUse(effector, effected))
		{
			case Formulas.SHIELD_DEFENSE_SUCCEED:
			{
				defence += effected.getShldDef();
				break;
			}
			case Formulas.SHIELD_DEFENSE_PERFECT_BLOCK:
			{
				defence = -1;
				break;
			}
		}
		
		double damage = 1;
		final boolean critical = Formulas.calcCrit(_criticalChance, effector, effected, skill);
		
		if (defence != -1)
		{
			// Trait, elements
			final double weaponTraitMod = Formulas.calcWeaponTraitBonus(effector, effected);
			final double generalTraitMod = Formulas.calcGeneralTraitBonus(effector, effected, skill.getTraitType(), true);
			final double weaknessMod = Formulas.calcWeaknessBonus(effector, effected, skill.getTraitType());
			final double attributeMod = Formulas.calcAttributeBonus(effector, effected, skill);
			final double pvpPveMod = Formulas.calculatePvpPveBonus(effector, effected, skill, true);
			final double randomMod = effector.getRandomDamageMultiplier();
			
			// Skill specific mods.
			final double weaponMod = effector.getAttackType().isRanged() ? 70 : 77;
			final double power = _power + effector.getStat().getValue(Stats.SKILL_POWER_ADD, 0);
			final double rangedBonus = effector.getAttackType().isRanged() ? attack + power : 0;
			final double critMod = critical ? Formulas.calcCritDamage(effector, effected, skill) : 1;
			double ssmod = 1;
			if (skill.useSoulShot())
			{
				if (effector.isChargedShot(ShotType.SOULSHOTS))
				{
					ssmod = 2 * effector.getStat().getValue(Stats.SHOTS_BONUS); // 2.04 for dual weapon?
				}
				else if (effector.isChargedShot(ShotType.BLESSED_SOULSHOTS))
				{
					ssmod = 4 * effector.getStat().getValue(Stats.SHOTS_BONUS);
				}
			}
			
			// ...................____________Melee Damage_____________......................................___________________Ranged Damage____________________
			// ATTACK CALCULATION 77 * ((pAtk * lvlMod) + power) / pdef            RANGED ATTACK CALCULATION 70 * ((pAtk * lvlMod) + power + patk + power) / pdef
			// ```````````````````^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^``````````````````````````````````````^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
			final double baseMod = (weaponMod * ((attack * effector.getLevelMod()) + power + rangedBonus)) / defence;
			damage = baseMod * ssmod * critMod * weaponTraitMod * generalTraitMod * weaknessMod * attributeMod * pvpPveMod * randomMod;
			damage *= effector.getStat().getValue(Stats.PHYSICAL_SKILL_POWER, 1);
			damage *= -((effector.getCurrentHp() * 2) / effector.getMaxHp()) + 2;
		}
		
		effector.doAttack(damage, effected, skill, false, false, critical, false);
	}
}
