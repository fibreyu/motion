package fun.snomis.motion.pojo;

public enum Level {
    CALM("CALM", 0), DEPRESSED("DEPRESSED", 1), HAPPY("HAPPY", 2), ANGRY("ANGRY", 3);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private Level(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static Boolean checkIndex(int index) {
        for (Level c : Level.values()) {
            if (c.getIndex() == index) {
                return true;
            }
        }
        return false;
    }

    // 普通方法
    public static Boolean checkName(String name) {
        for (Level c : Level.values()) {
            if (c.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
