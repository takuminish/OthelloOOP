package com.takuminish.othello.stone;

import com.takuminish.othello.CellPosition;
import com.takuminish.othello.StoneType;

/**
 * オセロの石
 */
public class StoneFactory {

    /**
     * インスタンス化させない
     */
    private StoneFactory() {

    }

    /**
     * 白石のインスタンスを生成
     * @param position 石の位置
     * @return 白石のStoneインスタンス
     */
    static public Stone createWhiteStone(final CellPosition position) {
        return new WhiteStone(position, StoneType.White);
    }

    /**
     * 黒石のインスタンスを生成
     * @param position 石の位置
     * @return 黒石のStoneインスタンス
     */
    static public Stone createBlackStone(final CellPosition position) {
        return new BlackStone(position, StoneType.Black);
    }

    /**
     * 石の未配置のインスタンスを生成
     * @param position 石の位置
     * @return 黒石のStoneインスタンス
     */
    static public Stone createEmptyStone(final CellPosition position) {
        return new EmptyStone(position, StoneType.Empty);
    }

    /**
     * 石の番兵のインスタンスを生成
     * @param position 石の位置
     * @return 番兵のStoneインスタンス
     */
    static public Stone createSentinelStone(final CellPosition position) {
        return new SentinelStone(position, StoneType.SENTINEL);
    }

    /**
     * プレイヤーの操作よる石の生成
     * @param position 石の位置
     * @param type 石の種類
     * @return 石のインスタンス
     */
    static public Stone createStone4PlayHand(final CellPosition position, final  StoneType type) {
        switch(type) {
            case White: return createWhiteStone(position);
            case Black: return createBlackStone(position);
            case Empty: return createEmptyStone(position);
        }

        return createEmptyStone(position);
    }
}
