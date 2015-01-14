package com.adjl.locus;

import java.util.Random;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

abstract class BeamImpl {

    private static PApplet sSketch;
    private static int[] sColours;

    private final float mTerminalVelocity;
    private final float mSize;
    private final int mColour;

    BeamImpl(BeamType type) {
        mTerminalVelocity = type.getTerminalVelocity();
        mSize = type.getSize();
        mColour = sColours[new Random().nextInt(sColours.length)];
    }

    static void setSketch(PApplet sketch) {
        sSketch = sketch;
        sColours = new int[] { sSketch.color(255, 0, 0), sSketch.color(0, 255, 0),
                sSketch.color(0, 0, 255), sSketch.color(0, 255, 255), sSketch.color(255, 0, 255),
                sSketch.color(255, 255, 0) };
    }

    static int nextInt(float bound) {
        return (int) sSketch.random(bound);
    }

    float getSize() {
        return mSize;
    }

    void move(PVector position, PVector velocity, PVector acceleration) {
        velocity.add(acceleration);
        velocity.limit(mTerminalVelocity);
        position.add(velocity);
    }

    void draw(PVector position, float length, float rotationX, float rotationZ) {
        float opacity = PApplet.map(Beam.MAX_LENGTH - length, 0.0f, Beam.MAX_LENGTH, 0.0f, 255.0f);
        sSketch.pushMatrix();
        sSketch.translate(position.x, position.y, position.z);
        sSketch.rotateX(rotationX);
        sSketch.rotateZ(rotationZ);
        sSketch.scale(1.0f, mSize, 1.0f);
        sSketch.beginShape(PConstants.LINES);
        sSketch.stroke(mColour);
        sSketch.vertex(0.0f, 0.0f, 0.0f);
        sSketch.stroke(mColour, opacity);
        sSketch.vertex(0.0f, length, 0.0f);
        sSketch.endShape(PConstants.CLOSE);
        sSketch.popMatrix();
    }
}
