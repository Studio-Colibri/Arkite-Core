/*
 * [경로] kr.arkite.core.ArkiteCore
 *
 * [역할] Arkite-Core 플러그인 메인 클래스. 모든 시스템 초기화/언로드/콘솔 브랜딩/이벤트/명령어 진입점.
 * [설계 근거] Bukkit 플러그인 표준 구조, Arkite-Core 고유 초기화/로그/설정 시스템 통합.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core;

import kr.arkite.core.command.CommandRegistry;
import kr.arkite.core.config.CoreConfig;
import kr.arkite.core.data.PlayerDataManager;
import kr.arkite.core.event.PlayerJoinListener;
import kr.arkite.core.event.PlayerQuitListener;
import kr.arkite.core.logging.LogManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArkiteCore extends JavaPlugin {

    private CoreConfig coreConfig;
    private PlayerDataManager playerDataManager;

    @Override
    public void onLoad() {
        //printStudioArkiteBanner("LOADING");
        LogManager.getLogger("Arkite-Core").info("Arkite-Core: onLoad() 진입");
    }

    @Override
    public void onEnable() {
        printStudioArkiteBanner("ENABLED");
        LogManager.getLogger("Arkite-Core").info("Arkite-Core: onEnable() 진입");

        // 설정 파일/객체 초기화
        this.coreConfig = new CoreConfig(this);

        // 데이터 매니저 초기화
        this.playerDataManager = new PlayerDataManager(this.getDataFolder());

        // 명령어 등록
        CommandRegistry.register(this);

        // 이벤트 리스너 등록
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        LogManager.getLogger("Arkite-Core").info("초기화 완료!");
    }

    @Override
    public void onDisable() {
        printStudioArkiteBanner("DISABLED");
        LogManager.getLogger("Arkite-Core").info("Arkite-Core: onDisable() 진입");
        // TODO: 데이터 세이브 등 필요한 마무리
    }

    // 콘솔 배너는 최종 버전 그대로 사용!
    private void printStudioArkiteBanner(String status) {
        String version = "1.0.0";
        String buildTime = java.time.LocalDateTime.now().toString().replace('T', ' ').substring(0, 19);
        String javaVer = System.getProperty("java.version");
        String osInfo = System.getProperty("os.name") + " " + System.getProperty("os.version");
        String paperVer = org.bukkit.Bukkit.getServer().getVersion();

        String line = "§d------------------------------------------------------------";

        getServer().getConsoleSender().sendMessage(line);
        getServer().getConsoleSender().sendMessage(
                "§b- §dSTUDIO §bArkite §7v" + version + "  §8|  §eStatus: " +
                        (status.equalsIgnoreCase("ENABLED") ? "§aENABLED" : status.equalsIgnoreCase("DISABLED") ? "§cDISABLED" : "§6LOADING")
        );
        getServer().getConsoleSender().sendMessage(line);

        getServer().getConsoleSender().sendMessage("§8- " + paperVer);
        getServer().getConsoleSender().sendMessage("§8- Java: " + javaVer);
        getServer().getConsoleSender().sendMessage("§8- OS: " + osInfo);
        getServer().getConsoleSender().sendMessage("§8- Build: " + buildTime);

        getServer().getConsoleSender().sendMessage("§a- Author: §7 한잎새");
        getServer().getConsoleSender().sendMessage("§a- Copy Right: §7© 2024 Studio Colibri. All Rights Reserved.");
        getServer().getConsoleSender().sendMessage(line);
    }

    // Getter 예시 (외부 접근 시)
    public CoreConfig getCoreConfig() {
        return coreConfig;
    }

    public PlayerDataManager getPlayerDataManager() { return playerDataManager; }

}
