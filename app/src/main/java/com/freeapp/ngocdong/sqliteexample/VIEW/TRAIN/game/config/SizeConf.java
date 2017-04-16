package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;

/**
 * Created by NgocDong on 14/03/2017.
 */

public class SizeConf {
    private GameSurface gameSurface;
    private final int BackgroundWidth;
    private final int BackgroundHeight;
    private int OptionSizeWidth;
    private int OptionSizeHeight;
    private int MenuPadding;
    private int BOSSSize;
    private int BOSSPaddingSize;
    private int CAMBSize;
    private int HealthyBarSize;
    private final int HealthyBarPaddingSize;
    private int SoliderSize;
    private int AttackButtonSize;
    private int SkillButtonSize;
    private int SkillButtonPadding;
    private int PencilSize;
    private int BlackHoleSize;
    private int AccelerationSize;
    private int BreakAllSize;
    private final int BreakAllDistanceSize;
    private final float BasicVelocity;

    private final float UnitSize;
    private int lineDistSize;
    private int lineDistHeight;
    private int partLineDistSize;

    public SizeConf(GameSurface gameSurface) {
        this.gameSurface = gameSurface;
        BackgroundWidth = gameSurface.getWidth();
        BackgroundHeight = gameSurface.getHeight();
        UnitSize = Min(BackgroundWidth, BackgroundHeight) / 23;
        OptionSizeWidth = BackgroundWidth / 18;
        OptionSizeHeight = BackgroundHeight / 18;
        MenuPadding = OptionSizeHeight / 5;
        BOSSSize = (int) (UnitSize);
        BOSSPaddingSize = (int) (BOSSSize / 5);
        CAMBSize = (int) (UnitSize * 0.9);
        HealthyBarSize = CAMBSize * 2 / 15;
        HealthyBarPaddingSize = HealthyBarSize / 3;
        SoliderSize = (int) (UnitSize * 1.5);
        AttackButtonSize = (int) (UnitSize * 3.5);
        SkillButtonSize = AttackButtonSize * 27 / 50;
        SkillButtonPadding = (int) (SkillButtonSize / 3.14);
        PencilSize = SoliderSize * 2 / 5;
        BlackHoleSize = SoliderSize * 18;
        AccelerationSize = SoliderSize * 6;
        BreakAllSize = (int) (BlackHoleSize * 1.2);
        BreakAllDistanceSize = (int) (1.2 * AccelerationSize);
        BasicVelocity = 0.062f;
        lineDistSize = (int) (19 * UnitSize);
        lineDistHeight = (int) (0.5 * UnitSize);
        partLineDistSize = lineDistSize / 30;
    }

    int Max(int a, int b) {
        if (a > b) return a;
        else return b;
    }

    int Min(int a, int b) {
        if (a < b) return a;
        else return b;
    }

    public float getUnitSize() {
        return UnitSize;
    }

    public int getBackgroundWidth() {
        return BackgroundWidth;
    }

    public int getBackgroundHeight() {
        return BackgroundHeight;
    }

    public int getOptionSizeWidth() {
        return OptionSizeWidth;
    }

    public int getOptionSizeHeight() {
        return OptionSizeHeight;
    }

    public int getMenuPadding() {
        return MenuPadding;
    }

    public int getBOSSSize() {
        return BOSSSize;
    }

    public int getBOSSPaddingSize() {
        return BOSSPaddingSize;
    }

    public int getCAMBSize() {
        return CAMBSize;
    }

    public int getHealthyBarSize() {
        return HealthyBarSize;
    }

    public int getHealthyBarPaddingSize() {
        return HealthyBarPaddingSize;
    }

    public int getSoliderSize() {
        return SoliderSize;
    }

    public int getAttackButtonSize() {
        return AttackButtonSize;
    }

    public int getSkillButtonSize() {
        return SkillButtonSize;
    }

    public int getSkillButtonPadding() {
        return SkillButtonPadding;
    }

    public int getPencilSize() {
        return PencilSize;
    }

    public int getBlackHoleSize() {
        return BlackHoleSize;
    }

    public int getAccelerationSize() {
        return AccelerationSize;
    }

    public int getBreakAllSize() {
        return BreakAllSize;
    }

    public int getBreakAllDistanceSize() {
        return BreakAllDistanceSize;
    }

    public float getBasicVelocity() {
        return BasicVelocity;
    }

    public int getLineDistSize() {
        return lineDistSize;
    }

    public int getPartLineDistSize() {
        return partLineDistSize;
    }

    public int getLineDistHeight() {
        return lineDistHeight;
    }
}
