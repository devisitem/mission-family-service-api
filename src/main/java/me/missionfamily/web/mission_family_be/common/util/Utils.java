package me.missionfamily.web.mission_family_be.common.util;

public class Utils {
    /**
     *
     * @param object
     * @return 객체가 널인지 확인
     */
    public static boolean isNull(Object object) {
        return (object == null);
    }

    /**
     *
     * @param object
     * @return 객체가 널이 아닌지 확인
     */
    public static boolean isNotNull(Object object) {
        return (object != null);
    }

    /**
     *
     * @param target
     * @return 문자열이 비었는지 확인 (널포함)
     */
    public static boolean isEmptyOrNull(String target) {
        return (target == null || "".equals(target));
    }

    /**
     *
     * @param target
     * @return 문자열이 비어있지 않은지 확인 (널포함)
     */
    public static boolean isNotEmptyAndNull(String target) {
        return (target != null && ! "".equals(target));
    }
}
