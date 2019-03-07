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
package org.l2j.gameserver.mobius.gameserver.network.serverpackets;

import com.l2jmobius.commons.network.PacketWriter;
import com.l2jmobius.gameserver.model.actor.instance.L2PcInstance;
import com.l2jmobius.gameserver.model.items.instance.L2ItemInstance;
import com.l2jmobius.gameserver.network.OutgoingPackets;

import java.util.Collection;

/**
 * @author Migi, DS
 */
public class ExReplyPostItemList extends AbstractItemPacket
{
	private final int _sendType;
	private final L2PcInstance _activeChar;
	private final Collection<L2ItemInstance> _itemList;
	
	public ExReplyPostItemList(int sendType, L2PcInstance activeChar)
	{
		_sendType = sendType;
		_activeChar = activeChar;
		_itemList = _activeChar.getInventory().getAvailableItems(true, false, false);
	}
	
	@Override
	public boolean write(PacketWriter packet)
	{
		OutgoingPackets.EX_REPLY_POST_ITEM_LIST.writeId(packet);
		packet.writeC(_sendType);
		packet.writeD(_itemList.size());
		if (_sendType == 2)
		{
			packet.writeD(_itemList.size());
			for (L2ItemInstance item : _itemList)
			{
				writeItem(packet, item);
			}
		}
		return true;
	}
}