import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class GraphNotes extends PApplet {



//GraphNotes
PImage logo, play, stop, join, split, x;
float logoX, logoY, logoW, logoH, playX, playY, playW, playH, stopX, stopY, stopW, stopH, 
      joinX, joinY, joinW, joinH, splitX, splitY, splitW, splitH, xX, xY, xW, xH;
boolean pSelect = false, sSelect = false, jSelect = false, spSelect = false, xSelect = false;
static Graph[] graphs = new Graph[4];
static boolean playing;
static boolean exit;
static boolean called;
static int index;
Button[] selector = new Button[8];
//Button[] selector2 = new Button[7];


public void setup(){
  logoX = width/100; logoY = height/22; playX = width/8+width/22; playY = height/16; 
  playW = width/7; playH = height/10; stopX = playX+playW+width/22; stopY = height/16;
  stopW = width/7; stopH = height/10; joinX = stopX+stopW+width/22; joinY= height/16;
  joinW = width/7; joinH = height/12; splitX = joinX+joinW+width/22; splitY = height/16;
  splitW = width/7; splitH = height/10; xX = splitX+splitW+width/44; xY = height/16;
  xW = width/16; xH = height/12; 
  index = 0;
  called = false;
  exit = false;
  playing = false;
  logo = loadImage("logo250px.png");
  play = loadImage("play.png");
  stop = loadImage("stop.png");
  join = loadImage("Join.png");
  split = loadImage("Split.png");
  x = loadImage("X.png");
  //Color para los botones
  int[] palette = new int[15];
  palette[0] = color(255, 255, 255); //white
  palette[1] = color(255, 153,153); //light red
  palette[2] = color(255, 204, 153); //light orange
  palette[3] = color(255, 255, 153); //light yellow
  palette[4] = color(204, 255, 153); //light green
  palette[5] = color(153, 204, 255); //light blue
  palette[6] = color(204, 135, 255); //light purple
  palette[7] = color(255, 204, 255); //light pink
  palette[8] = color(255, 0, 0);     //red
  palette[9] = color(255, 153, 51);   //orange
  palette[10] = color(255, 255, 0);   //yellow
  palette[11] = color(128, 255, 0); //green
  palette[12] = color(0, 128, 255); //blue
  palette[13] = color(127, 7, 255); //purple
  palette[14] = color(255, 0, 255); //pink
  
  //Setup de los graphs
  for (int i = 0, j = 0; i<graphs.length; i++, j+=16){
    //Graph(int bar, int id, int wid, int hei) int(random(1,4))*4
    graphs[i] = new Graph(j,16, (i+1), width, height);
  }
  //Vincular los grafos unos con otros
  for (int i = 1; i<graphs.length; i++){
    graphs[i-1].vertex.get(15).setTarget(graphs[i].vertex.get(0));
  }

  //Setup de los botones
  for (int i = 1; i<9; i++){
    selector[i-1] = new Button(width/13, ((height/6)+(i*height)/11),
    width/22, width/22, palette[i-1], (i-1));
  }
  //for (int i = 1, j = 8; i<8; i++, j++){
  //  selector2[i-1] = new Button(width - width/13, ((height/6)+(i*height)/11),
  //  width/22, width/22, palette[j], (j));
  //}
  
  
  //<>//
};

public void draw(){
  background(0); //0 = black
  //image(PImage, x, y, width, height)
  image(logo,logoX,logoY, width/8, height/6);
  
  showPS(); //PLAY & STOP 
  
  for (int i = 0; i<8; i++){
    selector[i].show();
  }
  
  //for (int i = 0; i<selector2.length; i++){
  //  selector2[i].show();
  //}
  
  graphs[0].showRecursive(graphs[0].vertex.get(0));
  
  //Reemplazar PITCH de VERTEX seleccionado
  if (!playing){
    for (int i = 0; i<selector.length; i++){
      if (selector[i].isSelected()){
        for (int j = 0; j<graphs.length; j++){
        graphs[j].replace(selector[i].getPalette(), selector[i].getID());
        }
      }
    }
}

  //Arrastrar vértice
  if (!playing){
  for (int i = 0; i<graphs.length; i++){
    if (mousePressed){
    graphs[i].setVertexY(PApplet.parseFloat(mouseY));
    graphs[i].setVertexX(PApplet.parseFloat(mouseX));
    }
  }
}
}

public void mouseClicked(){
  //SELECCIONAR BOTONES
  for (int i = 0; i<selector.length; i++){
    if (mouseX<=(width/13 + width/22)){
      selector[i].hover(mouseX, mouseY);
  }}
  
  //for (int i = 0; i<selector2.length; i++){
  //  if (mouseX>=(width-width/13)){
  //    selector2[i].hover(mouseX, mouseY);
  //}
  //}
};

public void showPS(){
  if (pSelect){
    play = loadImage("playSel.png");
    image(play, playX, playY, playW, playH);
  }
  else{
    play = loadImage("play.png");
    image(play, playX, playY, playW, playH);
  }
  
  if (sSelect){
    stop = loadImage("stopSel.png");
    image(stop, stopX, stopY, stopW, stopH);
  }
  else {
    stop = loadImage("stop.png");
    image(stop, stopX, stopY, stopW, stopH);
  }
  
  if (jSelect){
    join = loadImage("JoinSel.png");
    image(join, joinX, joinY, joinW, joinH);
  }
  else{
    join = loadImage("Join.png");
    image(join, joinX, joinY, joinW, joinH);
  }
  
  if (spSelect){
    split = loadImage("SplitSel.png");
    image(split, splitX, splitY, splitW, splitH);
  }
  else{
    split = loadImage("Split.png");
    image(split, splitX, splitY, splitW, splitH);
  }
  
  if (xSelect){
    x = loadImage("Xsel.png");
    image(x, xX, xY, xW, xH);
  }
  else{
    x = loadImage("X.png");
    image(x, xX, xY, xW, xH);
  }
}

public void mousePressed(){

  //play button
  if (mouseX>=playX && mouseX<=(playX + playW) && mouseY>=playY 
  && mouseY<=(playY + playH)){
    for (int i = 0; i<graphs.length;i++){
      graphs[i].deselect();
    }
    pSelect = true;
  }
  else{
    for (int i = 0; i<graphs.length;i++){
      graphs[i].deselect();
    }
    pSelect = false;
  }
  //stop button
  if (mouseX>=stopX && mouseX<=(stopX + stopW) && mouseY>=stopY 
  && mouseY<=(stopY + stopH)){
    for (int i = 0; i<graphs.length;i++){
      graphs[i].deselect();
    }
    sSelect = true;
  }
  else{
    for (int i = 0; i<graphs.length;i++){
      graphs[i].deselect();
    }
    sSelect = false;
  }
  
  //join button
  if (mouseX>=joinX && mouseX<=(joinX + joinW) && mouseY>=joinY 
  && mouseY<=(joinY + joinH)){
    jSelect = true;
  }
  else{
    jSelect = false;
  }
  
  //split button
  if (mouseX>=splitX && mouseX<=(splitX + splitW) && mouseY>=splitY 
  && mouseY<=(splitY + splitH)){
    spSelect = true;
  }
  else{
    spSelect = false;
  }
  
  //X button
  if (mouseX>=xX && mouseX<=(xX + xW) && mouseY>=xY 
  && mouseY<=(xY + xH)){
    for (int i = 0; i<graphs.length;i++){
      graphs[i].deselect();
    }
    xSelect = true;
  }
  else{
    for (int i = 0; i<graphs.length;i++){
      graphs[i].deselect();
    }
    xSelect = false;
  }
  
  //SELECCIONAR VERTEX
  if (!sSelect){
    for (int i = 0; i<graphs.length; i++){
    graphs[i].hovers(mouseX, mouseY);
    }
  } 
}

public void mouseReleased(){
  println("called: "+called);
  println("playing: "+playing);
  println("exit: "+exit);
  if(!called){
    playGraph.start();
    called = true;
  }

  if (pSelect){
    pSelect=false;
    for (int i = 0; i<graphs.length; i++){
    graphs[i].setPlaying(true);
    }
    playing = true;
    println("playing");
  }
    
  if (sSelect){
    sSelect=false;
    playing = false;
    for (int i = 0; i<graphs.length; i++){
      graphs[i].setPlaying(false);
    }
    println("stopped");
    
    //DESELECT BUTTONS
    for (int i = 0; i<selector.length;i++){
      selector[i].selected = false;
    }
  }
  
  if(jSelect){
    jSelect = false;
  }
  
  if(spSelect){
    spSelect = false;
  }
  
  if (xSelect){
    exit();
  }
}

static Thread playGraph = new Thread(){
  @Override
  public void run(){
    do{
      if (playing){
        //for (index=0; index<graphs.length; index++){
        //  graphs[index].playAll();
        //}
        graphs[0].playRecursive(graphs[0].vertex.get(0));
        index = 0;
        playing = false;
      }
      //playing = true;
      println("running");
    }
    while(!exit);
  }
};
class Button{
  
int palette; //Valor RGB del botón
int x, y, w, h; //Coordenadas x, y, width, height
boolean selected = false; //Inicia deseleccionado
int id;

//Constructor
  Button(int x, int y, int w, int h, int palette, int id){
    this.palette = palette;
    this.x = x;
    this.y = y;
    this.w = w;
    this.h = h;
    this.id = id;
  }

public void show(){ //Mostrar el botón
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
public void hover(int mX, int mY){ //mX = mouseX, mY = mouseY

  if (dist(this.x, this.y, mX, mY)<(this.h/2)){ 
    this.selected = true;
  }
  else{
    this.selected = false;
  }
  
}

public int getPalette(){return this.palette;}
public int getID(){return this.id;}
public boolean isSelected(){return this.selected;}

}
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
  this.barline = PApplet.parseByte(bar/4);
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

public void show(){
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

public void showRecursive(Vertex current){
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

public void hovers(int mX, int mY){
  for (int i = 0; i<this.bar; i++){
    //vertex.get(i).noteStop();
    vertex.get(i).hovers(mX, mY);
  }
}

public void setVertexY(float y){
  for (int i = 0; i<this.bar; i++){
    if (this.vertex.get(i).isSelected()){
      this.vertex.get(i).setY(y);
    }
  }
}

public void setVertexX(float x){
  for (int i = 0; i<this.bar; i++){
    if (this.vertex.get(i).isSelected()){
      this.vertex.get(i).setX(x);
    }
  }
}

public void replace(int palette, int id){
  for (int i = 0; i<this.bar; i++){
    if (this.vertex.get(i).isSelected()){
      this.vertex.get(i).setHue(palette, id);
    }
  }
}

public void playAll(){
  for(int i =0; i<bar; i++){
    if (this.playing){
    vertex.get(i).notePlay();}
    else{break;}
  }
}

public void playRecursive(Vertex current){
  
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

public void setPlaying(boolean playing){
  this.playing = playing;
}

public void deselect(){
  for (int i = 0; i<vertex.size(); i++){
    vertex.get(i).selected = false;
  }
}
}
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
  int hue;
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
  
  public void setTarget(Vertex ver){
    this.target.add(ver);
  }
  
  public void hovers(int mX, int mY){
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
  
  public float getX(){return this.x;}
  public float getY(){return this.y;}
  public int getSize() {return this.size;}
  public boolean isSelected() {return this.selected;}
  public int getHue(){return this.hue;}

  public void setY(float y){
    this.y = y;
  }
  
  public void setX(float x){
    this.x = x;
  }
  
  public void setHue(int palette, int pitch){
    if (pitch!=this.pitch){
    this.hue = palette;
    this.pitch = pitch;
    this.note = null;
    this.setNote(this.pitch);
    }
  }

//Asignar archivo .wav dependiendo del pitch indicado
  public void setNote(int pitch){
  switch(pitch){
      case 0: //rest
      this.note = new SoundFile(GraphNotes.this, "Piano/1-16/C4.wav");
      this.note.amp(0.0f);
      this.hue = color(255); //WHITE
        break;
        
      case 1: //C
        this.note = new SoundFile(GraphNotes.this, "Piano/1-16/C4.wav");
        this.note.amp(1.0f);
        this.hue = color(255, 153,153); //light red
        break;
        
      case 2: //D
        this.note = new SoundFile(GraphNotes.this, "Piano/1-16/D4.wav");
        this.note.amp(1.0f);
        this.hue = color(255, 204, 153); //light orange
        break;
        
      case 3: //E
        this.note = new SoundFile(GraphNotes.this, "Piano/1-16/E4.wav");
        this.note.amp(1.0f);
        this.hue = color(255, 255, 153); //light yellow
        break;
        
      case 4: //F
        note = new SoundFile(GraphNotes.this, "Piano/1-16/F4.wav");
        this.note.amp(1.0f);
        this.hue = color(204, 255, 153); //light green
        break;
        
      case 5: //G
        note = new SoundFile(GraphNotes.this, "Piano/1-16/G4.wav");
        this.note.amp(1.0f);
        this.hue = color(153, 204, 255); //light blue
        break;
        
      case 6: //A
        note = new SoundFile(GraphNotes.this, "Piano/1-16/A4.wav");
        this.note.amp(1.0f);
        this.hue = color(204, 135, 255); //light purple
        break;
        
      case 7: //B
        note = new SoundFile(GraphNotes.this, "Piano/1-16/B4.wav");
        this.note.amp(1.0f);
        this.hue = color(255, 204, 255); //light pink
        break;
    }
  
  }
  
  public void noteStop(){
    if(this.note.isPlaying()){
      this.note.stop();
    }
  }
  
  public void notePlay(){
    this.selected = true;
    this.note.play();
    delay(time[1]);
    this.selected = false;
  }
  
  public int getTargetSize(){return this.target.size();}
  
  public boolean hasTarget(){
    if (this.target.isEmpty()){
    return false;
    }
    else
    {
    return true;
    }
  }
}
  public void settings() {  fullScreen(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "GraphNotes" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
