/*
 * [경로] kr.arkite.core.logging.ArkiteLogger
 *
 * [역할] Arkite-Core 및 서브플러그인의 파일 로그 기록 담당. 일자별, 플러그인별 분리.
 * [설계 근거] Java 표준 Logger 대신 Arkite 전용 파일 기반 로그로 통합 관리.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.logging;

import org.bukkit.plugin.Plugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ArkiteLogger {
    private final String pluginName;
    private final File logFile;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ArkiteLogger(String pluginName) {
        this.pluginName = pluginName;
        this.logFile = resolveLogFile();
    }

    private File resolveLogFile() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String serverRoot = System.getProperty("user.dir"); // 항상 서버 루트 (OS 무관)
        File dir = new File(serverRoot, "Arkite-Log/" + date + "/");
        if (!dir.exists()) dir.mkdirs();
        return new File(dir, pluginName + ".log");
    }

    private String now() {
        return LocalDateTime.now().format(dtf);
    }

    private void write(String msg) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true))) {
            bw.write(msg);
            bw.newLine();
        } catch (IOException e) {
            // 로그 파일 기록 실패시, Console에만 최소한의 에러 출력
            System.err.println("[ArkiteLogger] 로그 파일 기록 실패: " + e.getMessage());
        }
    }

    public synchronized void info(String msg) {
        write("[INFO] " + now() + " - " + msg);
    }

    public synchronized void warn(String msg) {
        write("[WARN] " + now() + " - " + msg);
    }

    public synchronized void error(String msg) {
        write("[ERROR] " + now() + " - " + msg);
    }
}
