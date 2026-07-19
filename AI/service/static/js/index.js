// Live2D Reader Initialization
let live2dReaderApi=null;
async function initLive2dReader(){
  try{
    const canvas=document.getElementById('live2dCanvas');if(!canvas||live2dReaderApi)return;
    if(!window.PIXI||!window.PIXI.Application)return;
    const Live2DModel=window.PIXI.live2d&&(window.PIXI.live2d.Live2DModel||window.PIXI.live2d.default);
    if(!Live2DModel||!Live2DModel.from)return;
    const app=new window.PIXI.Application({view:canvas,width:180,height:240,backgroundAlpha:0,antialias:true,autoDensity:true,resolution:window.devicePixelRatio||1});
    const model=await Live2DModel.from('/static/live2d/yili/yili.model3.json',{autoInteract:true,autoUpdate:true,motionPreload:'ALL',idleMotionGroup:'Idle'});
    if(!model)return;app.stage.addChild(model);
    try{model.anchor.set(0.5,0.5);const b=model.getBounds();const s=Math.min(180/Math.max(1,b.width)*0.85,240/Math.max(1,b.height)*0.9);model.scale.set(s);model.x=90;model.y=140}catch(e){}
    live2dReaderApi={startTalking(){},stopTalking(){}};
  }catch(e){console.warn('Live2D init:',e.message)}
}
window.addEventListener('load',()=>setTimeout(initLive2dReader,1000));