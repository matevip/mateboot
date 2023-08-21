export interface IRouteMenu {
    name:string;
    path:string;
    component?:string;
    children?:Array<IRouteMenu>,
    redirect?:string,
    meta:IRouteMate,
  
}
export interface IRouteMate{
    title:string;
    icon:string;
    type:string;
    affix?:boolean,
    parentPath?:string,
    hidden?:boolean
    tag?: string
}
