package com.adjl.locus;

import com.adjl.virtualcamera.VirtualCamera;

import processing.core.PApplet;
import processing.core.PConstants;

public class Locus extends PApplet {

    private final float mStrokeWeight;
    private final int mBackground;
    private final int mFill;

    private LocusBeams mBeams;
    private LocusWorld mWorld;
    private VirtualCamera mCamera;
    private boolean mRunning;

    public Locus() {
        mStrokeWeight = 2.0f;
        mBackground = color(0, 0, 0);
        mFill = color(0, 0, 0);
    }

    @Override
    public void setup() {
        size(displayWidth, displayHeight, PConstants.P3D);
        rectMode(PConstants.CENTER);
        noCursor();
        fill(mFill);
        strokeWeight(mStrokeWeight);
        mWorld = new LocusWorld(this, 2000.0f, 2000.0f, 2000.0f);
        mCamera = new VirtualCamera(this, mWorld, 50.0f, 5.0f);
        mBeams = new LocusBeams(this, mWorld);
        mRunning = true;
    }

    @Override
    public void draw() {
        background(mBackground);
        mWorld.draw();
        if (mRunning) {
            mBeams.update();
        }
        mBeams.draw();
        mCamera.set();
    }

    @Override
    public void keyPressed() {
        if (key == 'p') {
            mRunning = !mRunning;
        } else {
            mCamera.move(key);
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[] { "--present", com.adjl.locus.Locus.class.getName() });
    }
}
