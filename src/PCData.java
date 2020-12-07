public final class PCData {

    /**
     * 任务相关的数据
     */
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d){
        intData = Integer.valueOf(d);
    }

    public int getData(){
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
