(function(e){function t(t){for(var o,c,u=t[0],s=t[1],a=t[2],p=0,f=[];p<u.length;p++)c=u[p],r[c]&&f.push(r[c][0]),r[c]=0;for(o in s)Object.prototype.hasOwnProperty.call(s,o)&&(e[o]=s[o]);l&&l(t);while(f.length)f.shift()();return i.push.apply(i,a||[]),n()}function n(){for(var e,t=0;t<i.length;t++){for(var n=i[t],o=!0,u=1;u<n.length;u++){var s=n[u];0!==r[s]&&(o=!1)}o&&(i.splice(t--,1),e=c(c.s=n[0]))}return e}var o={},r={app:0},i=[];function c(t){if(o[t])return o[t].exports;var n=o[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,c),n.l=!0,n.exports}c.m=e,c.c=o,c.d=function(e,t,n){c.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},c.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},c.t=function(e,t){if(1&t&&(e=c(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(c.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)c.d(n,o,function(t){return e[t]}.bind(null,o));return n},c.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return c.d(t,"a",t),t},c.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},c.p="/";var u=window["webpackJsonp"]=window["webpackJsonp"]||[],s=u.push.bind(u);u.push=t,u=u.slice();for(var a=0;a<u.length;a++)t(u[a]);var l=s;i.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"56d7":function(e,t,n){"use strict";n.r(t);n("cadf"),n("551c"),n("f751"),n("097d");var o=n("2b0e"),r=n("5132"),i=n.n(r),c=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[e._l(e.programs,function(t){return n("ul",{staticStyle:{float:"left"}},[n("h2",[e._v(e._s(t.computerName))]),e._l(t.content,function(t){return n("li",[e._v(e._s(t))])})],2)}),n("OneComputer")],2)},u=[],s=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}})},a=[],l={name:"OneComputer",components:{},props:{}},p=l,f=n("2877"),d=Object(f["a"])(p,s,a,!1,null,null,null),h=d.exports,m={name:"app",data:function(){return{programs:[]}},components:{OneComputer:h},methods:{deleteSocket:function(e){for(var t=0;t<this.programs.length;t++)if(this.programs[t].id===e)return this.programs.splice(t,1),void console.log("silindi")},addEvent:function(e){console.log(e);for(var t=0;t<this.programs.length;t++)if(this.programs[t].id===e.id)return void this.programs[t].content.push(e.text)}},sockets:{disconnect:function(){this.programs=[]},connect:function(){console.log("connected");var e={client:"view"};this.$socket.emit("connected",e)},programAdded:function(e){console.log(e);var t={id:e.id,computerName:e.computerName,content:[]};this.programs.push(t)},programDeleted:function(e){console.log(e+"silindi"),this.deleteSocket(e)},changed:function(e){this.addEvent(e)}}},g=m,v=Object(f["a"])(g,c,u,!1,null,null,null),b=v.exports,y=!1,_="";_=y?"localhost":"https://intense-cove-31943.herokuapp.com",o["a"].use(new i.a({debug:!1,connection:_,options:{path:"/socket.io"}})),o["a"].config.productionTip=!1,new o["a"]({render:function(e){return e(b)}}).$mount("#app")}});
//# sourceMappingURL=app.b72fa6d6.js.map