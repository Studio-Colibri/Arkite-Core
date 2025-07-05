/*
 * [경로] kr.arkite.core.logging.LogManager
 *
 * [역할] 플러그인/서브플러그인별 ArkiteLogger 싱글턴 관리, 외부에 전용 Logger 제공.
 * [설계 근거] 각 플러그인/모듈별로 별도 로그파일 분리, 전역 싱글턴 패턴.
 * [History]
 * - 2025-07-05: 최초 작성 (by ColibriStudio)
 */

package kr.arkite.core.logging;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {
    private static final Map<String, ArkiteLogger> loggerMap = new ConcurrentHashMap<>();

    public static ArkiteLogger getLogger(String pluginName) {
        return loggerMap.computeIfAbsent(pluginName, ArkiteLogger::new);
    }
}
