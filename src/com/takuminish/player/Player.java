package com.takuminish.player;

import com.takuminish.othello.Stone;
import com.takuminish.othello.StoneType;

/**
 * Playerインターフェース
 */
public interface Player {

    /**
     * 自分が使用する石の種類
     */
    public StoneType myStoneType();

    /**
     * 盤面に配置する石を決定する
     * @return 盤面に配置する石
     */
    public Stone playHand();
}
