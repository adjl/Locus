package com.adjl.locus;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class DownwardsBeam extends BeamImpl implements Beam {

    private final PVector mOrigin;
    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mRotationX;
    private final float mRotationZ;

    private float mLength;

    DownwardsBeam(PApplet sketch, BeamType type, LocusWorld world) {
        super(sketch, type);
        mOrigin = new PVector(nextInt(world.getWidth()), 1.0f - world.getHeight(),
                nextInt(world.getDepth()));
        mPosition = new PVector(mOrigin.x, mOrigin.y, mOrigin.z);
        mVelocity = new PVector(0.0f, type.getVelocity(), 0.0f);
        mAcceleration = new PVector(0.0f, type.getAcceleration(), 0.0f);
        mRotationX = 0.0f;
        mRotationZ = PConstants.PI;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.y - mLength * getSize() > 0.0f;
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mPosition.y - mOrigin.y) / getSize() + 1.0f, Beam.MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mRotationX, mRotationZ, mLength);
    }
}
