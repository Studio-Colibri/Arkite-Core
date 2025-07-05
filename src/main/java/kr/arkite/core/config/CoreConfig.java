/*
 * [경로] kr.arkite.core.config.CoreConfig
 *
 * [역할] Arkite-Core의 모든 전역 설정 항목을 관리. 커스텀 설정 접근 및 갱신 지원.
 * [설계 근거] ConfigLoader 기반, 추후 다양한 config 파일 분리/확장 고려.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.config;

import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.FileConfiguration;

public class CoreConfig {

    private final ConfigLoader loader;

    public CoreConfig(Plugin plugin) {
        this.loader = new ConfigLoader(plugin, "config.yml");
    }

    public String getWelcomeMessage() {
        return loader.getConfig().getString("welcome-message", "Welcome to the Arkite Server!");
    }

    public boolean isDebugMode() {
        return loader.getConfig().getBoolean("debug", false);
    }

    // 필요에 따라 더 많은 설정 값 getter 추가

    public void save() {
        loader.save();
    }

    public void reload() {
        loader.reload();
    }
}
