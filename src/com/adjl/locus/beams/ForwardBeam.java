package com.adjl.locus.beams;

import com.adjl.locus.LocusWorld;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

class ForwardBeam extends BeamImpl implements Beam {

    private final PVector mPosition;
    private final PVector mVelocity;
    private final PVector mAcceleration;
    private final float mOriginZ;

    private float mLength;

    ForwardBeam() {
        this(BeamType.getBeamType());
    }

    private ForwardBeam(BeamType type) {
        super(type);
        mPosition = new PVector(nextInt(getWorld().getWidth()), -nextInt(getWorld().getHeight()),
                getWorld().getDepth() - 1.0f);
        mVelocity = new PVector(0.0f, 0.0f, -type.getVelocity());
        mAcceleration = new PVector(0.0f, 0.0f, -type.getAcceleration());
        mOriginZ = mPosition.z;
    }

    @Override
    public boolean isGone(LocusWorld world) {
        return mPosition.z + mLength * getSize() < 0.0f;
    }

    @Override
    public void move() {
        move(mPosition, mVelocity, mAcceleration);
        mLength = PApplet.min((mOriginZ - mPosition.z) / getSize() + 1.0f, MAX_LENGTH);
    }

    @Override
    public void draw() {
        draw(mPosition, mLength, PConstants.HALF_PI, 0.0f);
    }
}
