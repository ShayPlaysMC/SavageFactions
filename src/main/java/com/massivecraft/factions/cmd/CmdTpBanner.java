package com.massivecraft.factions.cmd;

import com.massivecraft.factions.Conf;
import com.massivecraft.factions.P;
import com.massivecraft.factions.listeners.FactionsPlayerListener;
import com.massivecraft.factions.struct.Permission;
import com.massivecraft.factions.util.WarmUpUtil;
import com.massivecraft.factions.zcore.util.SmokeUtil;
import com.massivecraft.factions.zcore.util.TL;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class CmdTpBanner extends FCommand
{
    public  CmdTpBanner(){
        super();

        this.aliases.add("tpbanner");

        this.permission = Permission.TPBANNER.node;
        this.disableOnLock = true;

        senderMustBePlayer = true;
        senderMustBeMember = true;
        senderMustBeModerator = false;
        senderMustBeColeader = false;
        senderMustBeAdmin = false;
    }

    @Override
    public void perform(){
        final FactionsPlayerListener fpl = new FactionsPlayerListener(P.p);

        if (fpl.bannerLocations.containsKey(fme.getTag())){
            fme.msg(TL.COMMAND_TPBANNER_SUCCESS);
            this.doWarmUp(WarmUpUtil.Warmup.BANNER, TL.WARMUPS_NOTIFY_TELEPORT, "Banner", new Runnable() {
                @Override
                public void run() {
                    me.teleport(fpl.bannerLocations.get(fme.getTag()));
                }
            }, this.p.getConfig().getLong("warmups.f-banner", 0));
        } else {
            fme.msg(TL.COMMAND_TPBANNER_NOTSET);
        }

    }

    @Override
    public TL getUsageTranslation() {
        return TL.COMMAND_TPBANNER_DESCRIPTION;
    }
}
