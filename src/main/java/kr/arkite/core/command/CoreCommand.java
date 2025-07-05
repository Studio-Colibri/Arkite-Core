/*
 * [경로] kr.arkite.core.command.CoreCommand
 *
 * [역할] Arkite-Core의 기본 명령어(/core) 처리 담당. /core stat 등 스탯, 경험치 안내.
 * [설계 근거] CommandExecutor 패턴을 적용하여 플러그인 주입 방식으로 설계, 유지보수성과 테스트성 향상.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.command;

import kr.arkite.core.ArkiteCore;
import kr.arkite.core.data.PlayerSession;
import kr.arkite.core.stat.PlayerExpData;
import kr.arkite.core.stat.PlayerStatData;
import kr.arkite.core.stat.StatType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoreCommand implements CommandExecutor {

    private final ArkiteCore plugin;

    public CoreCommand(ArkiteCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§c플레이어만 사용할 수 있는 명령어입니다.");
            return true;
        }

        if (args.length > 0 && args[0].equalsIgnoreCase("stat")) {
            PlayerSession session = plugin.getPlayerDataManager().getOrCreate(player.getUniqueId(), player.getName());
            PlayerStatData stat = session.getStatData();
            PlayerExpData exp = session.getExpData();

            player.sendMessage("§b[Arkite-Core] §f나의 스탯 정보");
            for (StatType type : StatType.values()) {
                player.sendMessage("§7- " + type.name() + ": §f" + stat.get(type));
            }
            player.sendMessage("§7- 레벨: §f" + exp.getLevel() + "   §7경험치: §f" + exp.getExp());
            return true;
        }

        // 기본 도움말 안내
        player.sendMessage("§b[Arkite-Core] §7서버 정보 및 명령어 도움말:");
        player.sendMessage("§e/core stat §f- 내 스탯/경험치 보기");
        player.sendMessage("§e/core reload §f- 코어 설정 리로드");
        player.sendMessage("§e/core info §f- 현재 플러그인/버전 정보");
        return true;
    }
}
