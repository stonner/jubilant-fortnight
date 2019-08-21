package zcq.myjpa.common.enums;

/**
 * @author zhengchuqin
 * @history 2019-08-01 zhengchuqin 新建
 * @since JDK1.8
 */
public enum ApplyType {
    NOT_SUBMIT("1", "待提交"), //待提交
    PROCESSING("2", "处理中"), //处理中
    COMPLETED("3", "完成");   //完成

    ApplyType(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static ApplyType get(String status) {

        for (ApplyType t : ApplyType.values()) {
            if (t.status.equals(status)) {
                return t;
            }
        }
        return null;
    }

    private String status;

    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
