var at_upload=function(E,D,A,C){
	if(!E||!D)return;
	this.complete=true;
	this.reuqestUrl=D;
	this.uploadCount=0;
	this.nowUpload=0;
	this.container=(!A)?document.getElementsByTagName("body")[0]:A;
	this.button=this.createSwf(E);
	this.insertButton();
	this.loadingContainer=document.getElementById("at_upload");
	this.loadinput=this.loadingContainer.getElementsByTagName("input")[0];
	this.uploadGroupForAS=new Array();
	this.fileNameLength=C||128;
	var B=this;
	window.setTimeout(function(){
		B.loadinput.value=""
	}
	,100)
};
at_upload.prototype={
	hasComplete:function(){
		return this.complete
	}
	,setUploadState:function(B){
		this.complete=Boolean(B);
		if(this.complete==true){
			try{
				this.onComplete()
			}
			catch(A){}
		}
	}
	,createSwf:function(E){
		var D=74,A=16,C=document.createElement("div"),B=("<object id=\"uploadFileApply"+"_ie"+"\" name=\"uploadFileApply"+"_ie"+"\" width=\""+D+"\" height=\""+A+"\" classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0\" align=\"middle\">"+"<param value=\""+E+"?request="+this.reuqestUrl+"\" name=\"movie\" />"+"<param value=\"high\" name=\"quality\" />"+"<param value=\"transparent\" name=\"wmode\" />"+"<param name=\"allowScriptAccess\" value=\"sameDomain\" />"+"<embed id=\"uploadFileApply"+"_ff"+"\" name=\"uploadFileApply"+"_ff"+"\" src=\""+E+"?request="+this.reuqestUrl+"\" width=\""+D+"\" height=\""+A+"\" wmode=\"transparent\" quality=\"high\" allowScriptAccess=\"sameDomain\" swLiveConnect=\"true\" type=\"application/x-shockwave-flash\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" />"+"</object>");
		C.innerHTML=B;
		return C
	}
	,barDisplay:function(A){
		this.loadingContainer.style.display="block";
		if(A=="none")this.loadingContainer.style.display="none"
	}
	,createBar:function(D,E,A){
		var I=this,H=document.createElement("dl");
		H.className="loadingbar";
		var C=(function(){
			var B=0;
			for(var A=0;A<D.length;A++)if(/[\u4e00-\u9fa5]/.test(D.charAt(A)))B+=2;
			else B+=1;
			if(B>I.fileNameLength)return D.substr(0,I.fileNameLength-1)+"...";
			else return D
		})(),F=document.createElement("dt");
		F.innerHTML=C+" ("+Math.floor(parseInt(E)/1000)+"KB)";
		F.className="tit";
		H.appendChild(F);
		var K=document.createElement("dd");
		K.className="bar";
		var L=document.createElement("span");
		K.appendChild(L);
		H.appendChild(K);
		var J=document.createElement("dd");
		J.className="del";
		J.style.display="block";
		var G=document.createElement("s");
		G.style.display="block";
		G.innerHTML="\u6b63\u5728\u4e0a\u4f20";
		J.appendChild(G);
		var B=document.createElement("a");
		B.style.display="none";
		B.innerHTML="\u5220\u9664";
		B.href="javascript:void(0)";
		B.onclick=function(){
			I.deleteBar(D,E,A)
		};
		J.appendChild(B);
		H.appendChild(J);
		this.loadingContainer.appendChild(H);
		return{
			"name":D,"size":E,"container":H,"percentage":L,"del":J
		}
	}
	,deleteBar:function(G,H,F){
		var A=999,D=null,E=G,J=H;
		for(var I=0;I<this.uploadGroupForAS.length;I++)if(this.uploadGroupForAS[I].name==E&&this.uploadGroupForAS[I].size==J){
			A=I;
			break
		}
		this.removeInput(A);
		D=this.uploadGroupForAS[A].dom.container;
		D.parentNode.removeChild(D);
		this.uploadGroupForAS.splice(A,1);
		if(this.uploadGroupForAS.length==0||!this.uploadGroupForAS.length)this.barDisplay("none");
		var C=null;
		if(navigator.appName.indexOf("Microsoft")!=-1)C=document.getElementById("uploadFileApply_ie");
		else C=document["uploadFileApply_ff"];
		C.remFile(E,J);
		try{
			this.onItemDelete(F,G,H)
		}
		catch(B){}if(this.hasAllNull()){
			try{
				this.onClear()
			}
			catch(B){}
		}
	}
	,hasAllNull:function(){
		var A=true;
		for(var B=0;B<this.uploadGroupForAS.length;B++)if(this.uploadGroupForAS[B]!=null){
			A=false;
			break
		}
		return A
	}
	,insertButton:function(){
		var A="<!--insertUploadButton-->";
		this.container.innerHTML=this.container.innerHTML.replace(A,this.button.innerHTML)
	}
	,insertNewUpload:function(C,D,A){
		this.barDisplay("block");
		this.uploadGroupForAS[A]={
			"name":C,"servername":null,"size":D,"dom":this.createBar(C,D,A)
		};
		try{
			this.onCreate(A,C,D)
		}
		catch(B){}
	}
	,insertWrite:function(C,A){
		this.uploadGroupForAS[A].dom.percentage.style.width=C+"px";
		try{
			this.onItemLoading(A,C)
		}
		catch(B){}
	}
	,insertComplete:function(C,A){
		this.insertInput(A,C);
		this.uploadGroupForAS[A].dom.del.getElementsByTagName("s")[0].style.display="none";
		this.uploadGroupForAS[A].dom.del.getElementsByTagName("a")[0].style.display="block";
		try{
			this.onItemComplete(A,C)
		}
		catch(B){}
	}
	,insertError:function(C,A){
		try{
			this.onError(A,C)
		}
		catch(B){}
	}
	,insertInput:function(B,E){
		this.uploadGroupForAS[B].servername=E;
		var D=this.uploadGroupForAS[B].name,F=this.uploadGroupForAS[B].size,A=this.uploadGroupForAS[B].servername,C=RegExp(A+",");
		if(!C.test(this.loadinput.value))this.loadinput.value+=(A+",")
	}
	,removeInput:function(B){
		var D=this.uploadGroupForAS[B].name,E=this.uploadGroupForAS[B].size,A=this.uploadGroupForAS[B].servername,C=A+",";
		this.loadinput.value=this.loadinput.value.replace(C,"")
	}
}