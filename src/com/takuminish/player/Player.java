package com.takuminish.player;

import com.takuminish.othello.CellPosition;
import com.takuminish.othello.stone.Stone;
import com.takuminish.othello.StoneType;

import java.util.List;

/**
 * Playerインターフェース
 */
public interface Player {

    /**
     * 自分が使用する石の種類
     */
    public StoneType myStoneType();

    /**
     * 自分が使用する石の盤面上の数を返す
     * @return 自分が使用する石の盤面上の数
     */
    public int myStoneNum();

    /**
     * 盤面に配置する石を決定する
     * @return 盤面に配置する石
     */
    public Stone playHand(final List<CellPosition> playPossibleCellPositionList);
}
