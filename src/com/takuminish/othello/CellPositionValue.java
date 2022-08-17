package com.takuminish.othello;

/**
 *
 */
public class CellPositionValue {
    /** オセロのセルの位置の値 */
    private final int value;

    /**
     *コンストラクタ
     *
     * @param value
     * @throws  値が負数です
     */
     CellPositionValue(final int value) throws Exception {
        // 負数は格納できない
        if (value < 0) {
            throw new Exception();
        }

        this.value = value;
    }

    /**
     * valueのGetter
     * @return value
     */
    public int getValue() {
        return this.value;
    }
}
