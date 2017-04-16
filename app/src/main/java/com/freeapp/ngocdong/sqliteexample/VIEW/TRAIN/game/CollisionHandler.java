package com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game;

import android.graphics.Rect;

import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.config.Configure;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.skill.Pencil_Bullet;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript.CAMBAT;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.core.GameSurface;
import com.freeapp.ngocdong.sqliteexample.VIEW.TRAIN.game.content.GameScript.GameScript;

import java.util.ArrayList;

/**
 * Created by NgocDong on 11/03/2017.
 */

public class CollisionHandler {
    private final Configure configure;
    Army army;
    GameScript gameScript;
    GameSurface gameSurface;

    public CollisionHandler(Army army, Configure configure, GameSurface gameSurface) {
        this.army = army;
        this.gameSurface = gameSurface;
        this.configure = configure;
    }

    public void setGameScript(GameScript gameScript) {
        this.gameScript = gameScript;
    }

    public void updatePoint() {
        if (gameScript != null) {
            // lay doi tuong Solider hien tai
            Solider solider = army.getSolider();
            if (solider != null) {
                try {
                    // xu ly va cham cac CAMBATANT
                    ArrayList<CAMBAT> tmp = gameScript.getcurrentRoom().getMatch().getCAMBATANT();
                    for (CAMBAT g : tmp) {
                        Rect currBound = g.getMybound();
                        // xu ly va cham solider va CAMBATANT
                        int graphicText_bullet = caseOf(solider.getBound(), currBound);
                        if (graphicText_bullet == 1) {
                            int y = vectoY(solider.getBound(), currBound);
                            solider.setMovingVector(solider.movingVectorX, y * Math.abs(solider.movingVectorY));
                            g.setMovingVector(g.movingVectorX, -y * Math.abs(g.movingVectorY));
                        } else if (graphicText_bullet == 2) {
                            int x = vectoX(solider.getBound(), currBound);
                            solider.setMovingVector(x * Math.abs(solider.movingVectorX), solider.movingVectorY);
                            g.setMovingVector(-x * Math.abs(g.movingVectorX), g.movingVectorY);
                        }
                        // Va cham giua các Cambat
                        for (CAMBAT g2 : tmp) {
                            if (!g.equals(g2)) {
                                Rect currBound2 = g2.getMybound();
                                int graphicText_graphicText = caseOf(currBound, currBound2);
                                if (graphicText_graphicText == 1) {
                                    int y = vectoY(currBound, currBound2);
                                    g.setMovingVector(g.movingVectorX, y * Math.abs(g.movingVectorY));
                                } else if (graphicText_graphicText == 2) {
                                    int x = vectoX(currBound, currBound2);
                                    g.setMovingVector(x * Math.abs(g.movingVectorX), g.movingVectorY);
                                }
                            }
                        }
                        // xu ly trúng đạn
                        for (Pencil_Bullet pb : army.getPencil_bullet_list()) {
                            Rect pbBound = pb.getBound();
                            int graphicText_pencil_Bullet = caseOf(pbBound,
                                    currBound);
                            if (graphicText_pencil_Bullet > 0) {
                                g.Attacked(pb.getDamage());
                                pb.Destroy();
                            }
                        }
                        // xử lý skill backhole
                        if (army.getBlackhole() != null) {
                            float deltaX = army.getBlackhole().getFocusX() - currBound.centerX();
                            float deltaY = army.getBlackhole().getFocusY() - currBound.centerY();
                            float D = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
                            float powD = (float) Math.sqrt(D);
                            float velocity = (float) (configure.getSizeConf().getBasicVelocity() * army.getBlackhole().getRadius() / powD);
                            if (g.getHealthy() > 250)
                                g.setMovingVector(deltaX, deltaY);// đổi véc tơ chuyển động kéo về tâm hố đen
                            else
                                g.setMovingVector(-deltaX, -deltaY);// gần hết máu đổi véc tơ chuyển động kéo ra khỏi tâm hố đen
                            g.setVELOCITY(velocity < configure.getSizeConf().getBasicVelocity() ? (float) (velocity * 5) : velocity);
                            if (D < army.getBlackhole().getRadius() * 2.8)
                                g.Attacked((int) (army.getBlackhole().getRadius() / powD));// cang gan damage cang cao
                        } else if (army.getBreakAll() != null) {// Xử lý break all, uu tien black hole so voi tang toc
                            g.setVELOCITY(0);
                        } else {// Không có sự kiện gì
                            g.setVELOCITY(configure.getSizeConf().getBasicVelocity());
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    int vectoX(Rect from, Rect to) {
        return from.centerX() > to.centerX() ? 1 : -1;
    }

    int vectoY(Rect from, Rect to) {
        return from.centerY() > to.centerY() ? 1 : -1;
    }

    int caseOf(Rect from, Rect to) {
        if (from != null && to != null) {
            float x = Min(from.right, to.right) - Max(from.left, to.left);// Kiểm tra điều kiện cần: right - left
            if (x >= 0) {// nếu thỏa điều kiện cần
                float y = Min(from.bottom, to.bottom) - Max(from.top, to.top);// Kiểm tra điều kiện đủ: top - bott
                if (y >= 0) {// Nếu thỏa điều kiện đủ
                    if (x > y) {
                        return 1;
                    }
                    return 2;
                }
            }
        }
        return 0;
    }

    float Max(float a, float b) {
        if (a > b) return a;
        else return b;
    }

    float Min(float a, float b) {
        if (a < b) return a;
        else return b;
    }
}
