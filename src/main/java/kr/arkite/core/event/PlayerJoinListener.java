/*
 * [경로] kr.arkite.core.event.PlayerJoinListener
 *
 * [역할] 플레이어 접속 시 처리(세션, 메시지, 초기화 등).
 * [설계 근거] Bukkit Listener, Join 이벤트별 분리.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.event;

import kr.arkite.core.ArkiteCore;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public class PlayerJoinListener implements Listener {
    // PlayerJoinListener
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ArkiteCore core = (ArkiteCore) Bukkit.getPluginManager().getPlugin("Arkite-Core");
        core.getPlayerDataManager().handleJoin(player);
        player.sendMessage("§b[Arkite-Core] §f데이터가 성공적으로 불러와졌습니다!");
    }
}
