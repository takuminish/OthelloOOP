package com.takuminish.othello;

import java.util.Objects;

/**
 * オセロのセルを表すクラス
 */
public class CellPosition {

    /** オセロのセルの位置(X方向) */
    private final CellPositionValue x;

    /** オセロのセルの位置(Y方向) */
    private final CellPositionValue y;

    /**
     * コンストラクタ
     * @param x オセロのセルの位置(X方向)
     * @param y オセロのセルの位置(Y方向)
     */
    public CellPosition(final CellPositionValue x, final CellPositionValue y) {
        this.x = x;
        this.y = y;
    }

    /**
     *  オセロのセルの位置(Y方向×盤面の幅+X方向)
     * @param boardWidth 盤面の幅
     */
    public int getCellPosition(final int boardWidth) {
        return this.y.getValue() * boardWidth + this.x.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return x.equals(that.x) && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
