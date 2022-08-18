package com.takuminish.player;

import com.takuminish.othello.CellPosition;
import com.takuminish.othello.Stone;

/**
 * Playerインターフェース
 */
public interface Player {
    /**
     * 盤面に配置する石を決定する
     * @return 盤面に配置する石
     */
    public Stone playHand();
}
