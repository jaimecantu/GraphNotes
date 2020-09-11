class Vertex {

  int size = width/100; //CONSTANTE
  int pitch; //A, B, C, ..., G
  ArrayList<Integer> edge = new ArrayList(); 
//Tipo de arista (numerador) {1/16, 2/16, 3/16, 4/16, 6/16, 8/16, 12/16, 16/16} 
  int[] time = {0, 4000/16, 8000/16, 12000/16, 16000/16, -1, 24000/16, -1, 32000/16, -1, -1, -1, 
                48000/16, -1, -1, -1, 4000}; 
                //Representa el costo de las aristas.
  float x, y; //Coordenadas en pantalla
  ArrayList<Vertex> target = new ArrayList(); //El vértice puede tener múltiples vértices adyacentes
  boolean selected; //El vértice se dibuja diferente dependiendo si está seleccionado o no.
  color hue;
  SoundFile note; //Archivo .wav con la nota seleccionada
  
  Vertex(){ //Constructor vacío. No utilizar
  }
  
  //Current vertex, target, edge
  Vertex(int pitch, int edge, float x, float y){
    this.pitch = pitch; //0, 1, 2, ..., 7
    //this.target.add(next); //null si no tiene target
    this.edge.add(edge);
    this.x = x;
    this.y = y;
    //Asignar archivo .wav dependiendo del pitch indicado
    this.setNote(pitch);
  }
  
  void setTarget(Vertex ver){
    this.target.add(ver);
  }
  
  void hovers(int mX, int mY){
    if(dist(mX, mY, this.x, this.y)<=this.size){
      this.selected = true;
      this.note.play();
      delay(time[this.edge.get(0)]);
      //this.note.stop();
    }
    else{
      this.selected = false;
    }
  }
  
  float getX(){return this.x;}
  float getY(){return this.y;}
  int getSize() {return this.size;}
  boolean isSelected() {return this.selected;}
  color getHue(){return this.hue;}

  void setY(float y){
    this.y = y;
  }
  
  void setX(float x){
    this.x = x;
  }
  
  void setHue(color palette, int pitch){
    if (pitch!=this.pitch){
    this.hue = palette;
    this.pitch = pitch;
    this.note = null;
    this.setNote(this.pitch);
    }
  }

//Asignar archivo .wav dependiendo del pitch indicado
  void setNote(int pitch){
  switch(pitch){
      case 0: //rest
      this.note = new SoundFile(GraphNotes.this, "Piano/1-16/C4.wav");
      this.note.amp(0.0);
      this.hue = color(255); //WHITE
        break;
        
      case 1: //C
        this.note = new SoundFile(GraphNotes.this, "Piano/1-16/C4.wav");
        this.note.amp(1.0);
        this.hue = color(255, 153,153); //light red
        break;
        
      case 2: //D
        this.note = new SoundFile(GraphNotes.this, "Piano/1-16/D4.wav");
        this.note.amp(1.0);
        this.hue = color(255, 204, 153); //light orange
        break;
        
      case 3: //E
        this.note = new SoundFile(GraphNotes.this, "Piano/1-16/E4.wav");
        this.note.amp(1.0);
        this.hue = color(255, 255, 153); //light yellow
        break;
        
      case 4: //F
        note = new SoundFile(GraphNotes.this, "Piano/1-16/F4.wav");
        this.note.amp(1.0);
        this.hue = color(204, 255, 153); //light green
        break;
        
      case 5: //G
        note = new SoundFile(GraphNotes.this, "Piano/1-16/G4.wav");
        this.note.amp(1.0);
        this.hue = color(153, 204, 255); //light blue
        break;
        
      case 6: //A
        note = new SoundFile(GraphNotes.this, "Piano/1-16/A4.wav");
        this.note.amp(1.0);
        this.hue = color(204, 135, 255); //light purple
        break;
        
      case 7: //B
        note = new SoundFile(GraphNotes.this, "Piano/1-16/B4.wav");
        this.note.amp(1.0);
        this.hue = color(255, 204, 255); //light pink
        break;
    }
  
  }
  
  void noteStop(){
    if(this.note.isPlaying()){
      this.note.stop();
    }
  }
  
  void notePlay(){
    this.selected = true;
    this.note.play();
    delay(time[1]);
    this.selected = false;
  }
  
  int getTargetSize(){return this.target.size();}
  
  boolean hasTarget(){
    if (this.target.isEmpty()){
    return false;
    }
    else
    {
    return true;
    }
  }
}
