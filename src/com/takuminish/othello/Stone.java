package com.takuminish.othello;

/**
 * オセロの石
 */
public class Stone {
    /** 石の位置 */
    private final CellPosition position;

    /** 石の種類 */
    private final StoneType type;

    /**
     * コンストラクタ
     * @param position 石の位置
     * @param type 石の種類
     */
    public Stone(final CellPosition position, final  StoneType type) {
        this.position = position;
        this.type = type;
    }


}
