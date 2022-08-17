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
     CellPositionValue(final int value) throws IllegalArgumentException {
        // 負数は格納できない
        if (value < 0) {
            throw new IllegalArgumentException();
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
