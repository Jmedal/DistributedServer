package com.example.worknet.common.constant;

/**
 * @Author: YunJieJiang
 * @Date: Created in 15:28 2019/7/7 0007
 */
public enum UserConst {
    /**
     * 公司
     */
    COMPANY(1),
    /**
     * 讲师
     */
    TEACHER(2),
    /**
     * 学生（应聘者）
     */
    STUDENT(3);

    private int value = 0;

    UserConst(int value) {
        this.value = value;
    }

    public int getState()
    {
        return value;
    }

    public  UserConst valueOf(int value){
        switch (value){
            case 1:
                return COMPANY;
            case 2:
                return TEACHER;
            case 3:
                return STUDENT;
            default:
                return null;
        }
    }
}

