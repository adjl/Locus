package com.adjl.locus.beams;

import com.adjl.locus.LocusWorld;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class RightBeam extends BeamImpl implements Beam {

    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mOriginX;

    private float mLength;

    RightBeam() {
        this(BeamType.getBeamType());
    }

    private RightBeam(BeamType type) {
        super(type);
        mPosition = new PVector(0.0f, -nextInt(getWorld().getHeight()), nextInt(getWorld()
                .getDepth()));
        mVelocity = new PVector(type.getVelocity(), 0.0f, 0.0f);
        mAcceleration = new PVector(type.getAcceleration(), 0.0f, 0.0f);
        mOriginX = mPosition.x;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.x - mLength * getSize() >= world.getWidth();
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mPosition.x - mOriginX) / getSize() + 1.0f, MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mLength, 0.0f, PConstants.HALF_PI);
    }
}
