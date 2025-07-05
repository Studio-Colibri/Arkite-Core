/*
 * [경로] kr.arkite.core.config.ConfigLoader
 *
 * [역할] Arkite-Core 설정 파일 로딩/저장/생성 유틸리티.
 * [설계 근거] Bukkit YamlConfiguration을 활용, 모든 설정 파일을 일관적으로 처리.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {

    private final Plugin plugin;
    private final File file;
    private FileConfiguration config;

    public ConfigLoader(Plugin plugin, String filename) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(filename, false);
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("[Arkite-Core] config 파일 저장 실패: " + e.getMessage());
        }
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }
}
