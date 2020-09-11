class Graph{

//Variables
int id, ex=0;
int songKey;
int bar;
int barline;
boolean playing = true;
int martinillo[] = {1,0,2,0,3,0,1,0,1,0,2,0,3,0,1,0,
                    3,0,4,0,5,0,0,0,3,0,4,0,5,0,0,0,
                    5,6,5,4,3,0,1,0,5,6,5,4,3,0,1,0,
                    2,0,5,0,1,0,0,0,2,0,5,0,1,0,0,0};
ArrayList<Vertex> vertex = new ArrayList();

Graph(int ex, int bar, int id, int wid, int hei){
  this.ex = ex;
  this.bar = bar;
  this.id = id;
  this.barline = byte(bar/4);
  //CREATE DEFAULT
  for (int i = 0; i<this.bar; i++, this.ex++){
    this.vertex.add(new Vertex(martinillo[this.ex], 1, wid/6 + (i*wid/22), 
    (this.id*hei/5) + random(hei/5-(hei/8)))); //Constructor = pitch, target, edge, x, y
  }
  //SET DEFAULT
  for (int i = 1; i<this.bar; i++){
    this.vertex.get(i-1).setTarget(this.vertex.get(i));
  }
}

void show(){
  //EDGES
  for(int j = 1; j<this.bar; j++){   
    stroke(255);
    strokeWeight(width/400);
    line(this.vertex.get(j).getX(), this.vertex.get(j).getY(), 
          this.vertex.get(j-1).getX(), this.vertex.get(j-1).getY());
  }
  //VERTEX
  for(int i = 0; i<this.bar; i++){
    if(this.vertex.get(i).isSelected()){
    fill(0);
    stroke(this.vertex.get(i).getHue()); strokeWeight(width/200);
    ellipse(this.vertex.get(i).getX(), this.vertex.get(i).getY(), 
            this.vertex.get(i).getSize(), this.vertex.get(i).getSize());
    }
    else{
    fill(this.vertex.get(i).getHue());
    noStroke();
    ellipse(this.vertex.get(i).getX(), this.vertex.get(i).getY(), 
            this.vertex.get(i).getSize(), this.vertex.get(i).getSize());
    }
  } 
}

void showRecursive(Vertex current){
  if(!current.hasTarget()){
  //CASO DE SALIDA: VERTEX NO TIENE TARGET
    if(current.isSelected()){
    fill(0);
    stroke(current.getHue()); strokeWeight(width/200);
    ellipse(current.getX(), current.getY(), 
            current.getSize(), current.getSize());
    }
    else{
    fill(current.getHue());
    noStroke();
    ellipse(current.getX(), current.getY(), 
            current.getSize(), current.getSize());
    }
  }
  else{
  //CASO RECURSIVO: AL MENOS 1 TARGET
  //DIBUJAR LÍNEAS
    for(int i = 0; i<current.getTargetSize(); i++){   
      stroke(255,255,255,150);
      strokeWeight(width/400);
      line(current.getX(), current.getY(), 
            current.target.get(i).getX(), current.target.get(i).getY());
    }
  //DIBUJAR VERTEX
    if(current.isSelected()){
      fill(0);
      stroke(current.getHue()); strokeWeight(width/200);
      ellipse(current.getX(), current.getY(), 
              current.getSize(), current.getSize());
    }
    else{
      fill(current.getHue());
      noStroke();
      ellipse(current.getX(), current.getY(), 
              current.getSize(), current.getSize());
    }
    for (int i = 0; i<current.getTargetSize(); i++){
      showRecursive(current.target.get(i));
    }
  }
}

void hovers(int mX, int mY){
  for (int i = 0; i<this.bar; i++){
    //vertex.get(i).noteStop();
    vertex.get(i).hovers(mX, mY);
  }
}

void setVertexY(float y){
  for (int i = 0; i<this.bar; i++){
    if (this.vertex.get(i).isSelected()){
      this.vertex.get(i).setY(y);
    }
  }
}

void setVertexX(float x){
  for (int i = 0; i<this.bar; i++){
    if (this.vertex.get(i).isSelected()){
      this.vertex.get(i).setX(x);
    }
  }
}

void replace(color palette, int id){
  for (int i = 0; i<this.bar; i++){
    if (this.vertex.get(i).isSelected()){
      this.vertex.get(i).setHue(palette, id);
    }
  }
}

void playAll(){
  for(int i =0; i<bar; i++){
    if (this.playing){
    vertex.get(i).notePlay();}
    else{break;}
  }
}

void playRecursive(Vertex current){
  
    if (!current.hasTarget()){
    //CASO DE SALIDA: Vértice NO tiene target
      if(this.playing){
        current.notePlay();
      }
    }
    else{
    //CASO RECURSIVO: Vértice tiene al menos un target
    if(this.playing){
        current.notePlay();
      }
    for(int i = 0; i<current.getTargetSize(); i++){
      playRecursive(current.target.get(i));
    }
    }
    
  

}

void setPlaying(boolean playing){
  this.playing = playing;
}

void deselect(){
  for (int i = 0; i<vertex.size(); i++){
    vertex.get(i).selected = false;
  }
}
}
