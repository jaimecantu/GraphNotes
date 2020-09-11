import processing.sound.*;

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


void setup(){
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
  color[] palette = new color[15];
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
  fullScreen();
  
  //<>//
};

void draw(){
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
    graphs[i].setVertexY(float(mouseY));
    graphs[i].setVertexX(float(mouseX));
    }
  }
}
}

void mouseClicked(){
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

void showPS(){
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

void mousePressed(){

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

void mouseReleased(){
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
