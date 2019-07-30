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

import org.l2j.gameserver.model.StatsSet;
import org.l2j.gameserver.model.actor.Creature;
import org.l2j.gameserver.model.actor.instance.Player;
import org.l2j.gameserver.model.effects.AbstractEffect;
import org.l2j.gameserver.model.holders.SkillHolder;
import org.l2j.gameserver.model.skills.Skill;

import static org.l2j.gameserver.util.GameUtils.isPlayer;

/**
 * @author Mobius
 */
public class AddSkillBySkill extends AbstractEffect
{
	private final int _existingSkillId;
	private final int _existingSkillLevel;
	private final SkillHolder _addedSkill;
	
	public AddSkillBySkill(StatsSet params)
	{
		_existingSkillId = params.getInt("existingSkillId");
		_existingSkillLevel = params.getInt("existingSkillLevel");
		_addedSkill = new SkillHolder(params.getInt("addedSkillId"), params.getInt("addedSkillLevel"));
	}
	
	@Override
	public boolean canPump(Creature effector, Creature effected, Skill skill)
	{
		return isPlayer(effector) && (effector.getSkillLevel(_existingSkillId) == _existingSkillLevel);
	}
	
	@Override
	public void pump(Creature effected, Skill skill)
	{
		if (isPlayer(effected))
		{
			((Player) effected).addSkill(_addedSkill.getSkill(), false);
		}
	}
	
	@Override
	public void onExit(Creature effector, Creature effected, Skill skill)
	{
		effected.removeSkill(_addedSkill.getSkill(), false);
	}
}
