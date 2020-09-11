class Button{
  
color palette; //Valor RGB del botón
int x, y, w, h; //Coordenadas x, y, width, height
boolean selected = false; //Inicia deseleccionado
int id;

//Constructor
  Button(int x, int y, int w, int h, color palette, int id){
    this.palette = palette;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.id = id;
  }

void show(){ //Mostrar el botón
  if (this.selected){
  stroke(this.palette);
  strokeWeight(height/58);
  noFill();
  smooth();
  ellipse(this.x, this.y, this.w, this.h);
  }
  else{
  noStroke();
  fill(this.palette);
  smooth();
  ellipse(this.x, this.y, this.w, this.h);
  }
}

//Verificar si el cursor está dentro del botón
void hover(int mX, int mY){ //mX = mouseX, mY = mouseY

  if (dist(this.x, this.y, mX, mY)<(this.h/2)){ 
    this.selected = true;
  }
  else{
    this.selected = false;
  }
  
}

color getPalette(){return this.palette;}
int getID(){return this.id;}
boolean isSelected(){return this.selected;}

}
