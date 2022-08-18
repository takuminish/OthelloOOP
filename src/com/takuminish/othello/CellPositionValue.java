package com.takuminish.othello;

/**
 * オセロのセルの位置の値を表すクラス
 */
public class CellPositionValue {
    /** オセロのセルの位置の値 */
    private final int value;

    /**
     *コンストラクタ
     *
     * @param value
     * @throws  IllegalArgumentException 値が負数です
     */
     public CellPositionValue(final int value) throws IllegalArgumentException {
        // 負数は格納できない
        if (value < 0) {
            throw new IllegalArgumentException("不正な値です。: " + value);
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
