class LocusBeams {

  final int chanceOfFiring = 1; // 1 in chanceOfFiring

  Platform platform;
  ArrayList<Beam> beams;
  IntList colours;

  LocusBeams(Platform platform) {
    this.platform = platform;
    beams = new ArrayList<Beam>();
    colours = new IntList();
    for (int i = 0; i < 6; i++) { // Number of colours
      colours.append(i);
    }
  }

  void update() {
    if (int(random(chanceOfFiring)) == 0) {
      beams.add(newBeam());
    }
    for (int i = beams.size() - 1; i >= 0; i--) {
      beams.get(i).move();
      if (beams.get(i).isGone(platform)) beams.remove(i);
    }
  }

  void draw() {
    pushMatrix();
    rotateY(PI);
    translate(-platform.width() / 2, 0, -platform.depth() / 2);
    for (Beam beam : beams) {
      beam.draw();
    }
    popMatrix();
  }

  Beam newBeam() {
    Beam beam = null;
    int direction = int(random(6)); // Number of directions
    switch (direction) {
      case 0: // Up
        beam = new UpwardsBeam(randomBeamType(), platform);
        break;
      case 1: // Down
        beam = new DownwardsBeam(randomBeamType(), platform);
        break;
      case 2: // Left
        beam = new LeftwardsBeam(randomBeamType(), platform);
        break;
      case 3: // Right
        beam = new RightwardsBeam(randomBeamType(), platform);
        break;
      case 4: // Forward
        beam = new ForwardsBeam(randomBeamType(), platform);
        break;
      case 5: // Backward
        beam = new BackwardsBeam(randomBeamType(), platform);
        break;
    }
    return beam;
  }

  BeamType randomBeamType() {
    return BeamType.values()[int(random(BeamType.values().length))];
  }
}