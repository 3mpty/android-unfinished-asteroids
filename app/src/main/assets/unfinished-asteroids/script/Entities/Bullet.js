ENGINE.Bullet = function(args) {

  Utils.extend(this, {

    direction: 0,
    speed: 300,
    lifespan: 2
  }, args);

  this.radius = 3;
    this.sprite = this.sprites[this.team];

};

ENGINE.Bullet.prototype = {

  constructor: ENGINE.Bullet,

  zIndex: 3,

  collidable: true,

    sprites: [
        [20, 53, 6, 6],
        [43, 53, 6, 6]
    ],

  collision: function(entity) {

    if (entity.hit) {

      if (entity.team !== this.team) {
        entity.hit(this);
        this.collection.remove(this);
      }

    }

  },

  step: function(delta) {

    /* lifespan */

    if ((this.lifespan -= delta) < 0) this.collection.remove(this);

    /* movement */

    this.x += Math.cos(this.direction) * this.speed * delta;
    this.y += Math.sin(this.direction) * this.speed * delta;

    /* wrap */

    app.game.wrap(this);
  },

  render: function() {

    //app.layer.fillStyle("#fff").fillRect(this.x - 4, this.y - 4, 8, 8);
      app.layer.save();
      app.layer.translate(this.x, this.y);
      app.layer.drawRegion(app.images.spritesheet, this.sprite, -this.width / 2, -this.height / 2);
      app.layer.restore();

  }

};