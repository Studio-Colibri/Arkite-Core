/*
 * [경로] kr.arkite.core.data.PlayerDataManager
 *
 * [역할] 모든 플레이어 세션을 메모리/파일로 관리, 접속/퇴장시 자동 저장/로드.
 * [설계 근거] 각 유저별 playerdata/UUID.yml 구조, YAML 사용.
 * [History]
 * - 2025-07-05: 파일 저장/불러오기 기능 추가 (by ColibriStudio)
 */

package kr.arkite.core.data;

import kr.arkite.core.stat.PlayerExpData;
import kr.arkite.core.stat.PlayerStatData;
import kr.arkite.core.stat.StatType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerDataManager {
    private final Map<UUID, PlayerSession> sessions = new ConcurrentHashMap<>();
    private final File dataFolder;

    public PlayerDataManager(File pluginDataFolder) {
        this.dataFolder = new File(pluginDataFolder, "playerdata");
        if (!dataFolder.exists()) dataFolder.mkdirs();
    }

    public PlayerSession getOrCreate(UUID uuid, String name) {
        return sessions.computeIfAbsent(uuid, k -> {
            PlayerSession session = load(uuid, name);
            if (session == null) session = new PlayerSession(uuid, name); // 파일 없으면 기본값
            return session;
        });
    }

    public void remove(UUID uuid) {
        sessions.remove(uuid);
    }

    // ==== 저장/불러오기 ====
    public void save(UUID uuid) {
        PlayerSession session = sessions.get(uuid);
        if (session == null) return;
        File file = new File(dataFolder, uuid + ".yml");
        YamlConfiguration yaml = new YamlConfiguration();
        yaml.set("name", session.getName());
        yaml.set("level", session.getLevel());
        yaml.set("exp", session.getExp());
        // TODO: 스탯, 경험치 등 추가 필드 저장
        yaml.set("stat.VIT", session.getStatData().get(StatType.VIT));
        yaml.set("stat.STR", session.getStatData().get(StatType.STR));
        yaml.set("stat.DEX", session.getStatData().get(StatType.DEX));
        yaml.set("stat.INT", session.getStatData().get(StatType.INT));
        yaml.set("stat.LUK", session.getStatData().get(StatType.LUK));
        yaml.set("exp.level", session.getExpData().getLevel());
        yaml.set("exp.exp", session.getExpData().getExp());

        try {
            yaml.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().warning("플레이어 데이터 저장 실패: " + uuid + " (" + e.getMessage() + ")");
        }
    }

    public PlayerSession load(UUID uuid, String name) {
        File file = new File(dataFolder, uuid + ".yml");
        if (!file.exists()) return null;
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        PlayerSession session = new PlayerSession(uuid, name);
        session.setName(yaml.getString("name", name));
        session.setLevel(yaml.getInt("level", 1));
        session.setExp(yaml.getDouble("exp", 0.0));
        // TODO: 스탯 등 추가 필드 로드
        // 불러오기
        PlayerStatData stat = session.getStatData();
        stat.set(StatType.VIT, yaml.getInt("stat.VIT", 5));
        stat.set(StatType.STR, yaml.getInt("stat.STR", 5));
        stat.set(StatType.DEX, yaml.getInt("stat.DEX", 5));
        stat.set(StatType.INT, yaml.getInt("stat.INT", 5));
        stat.set(StatType.LUK, yaml.getInt("stat.LUK", 5));
        PlayerExpData exp = session.getExpData();
        exp.setLevel(yaml.getInt("exp.level", 1));
        exp.setExp(yaml.getDouble("exp.exp", 0.0));
        return session;
    }

    public void saveAll() {
        sessions.keySet().forEach(this::save);
    }

    // 접속/퇴장에 맞춰 자동 호출
    public void handleJoin(Player player) {
        getOrCreate(player.getUniqueId(), player.getName());
    }
    public void handleQuit(Player player) {
        save(player.getUniqueId());
        remove(player.getUniqueId());
    }
}
