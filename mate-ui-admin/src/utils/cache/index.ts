import { Storage } from "@/utils/storage";
import CacheKey from "@/utils/cache/key";

// 缓存
class Cache {
  getToken = (): string => {
    return Storage.getItem(CacheKey.TokenKey) || "";
  };

  setToken = (value: string) => {
    Storage.setItem(CacheKey.TokenKey, value);
  };

  getMenu = (): string => {
    return Storage.getItem(CacheKey.MenuKey) || "";
  };

  setMenu = (value: string) => {
    Storage.setItem(CacheKey.MenuKey, value);
  };

  getLanguage = (): string => {
    return Storage.getItem(CacheKey.LangKey) || "zh-cn";
  };

  setLanguage = (value: string) => {
    Storage.setItem(CacheKey.LangKey, value);
  };
  
  getDark = (): string => {
    return Storage.getItem(CacheKey.DarkKey) || false;
  };

  setDark = (value: any) => {
    Storage.setItem(CacheKey.DarkKey, value);
  };

  removeDark = () => {
    Storage.removeItem(CacheKey.DarkKey);
  };
    
  getColor = (): string => {
    return Storage.getItem(CacheKey.ColorKey) || '#0058DC';
  };

  setColor = (value: any) => {
    Storage.setItem(CacheKey.ColorKey, value);
  };

  getLayout = (): string => {
    return Storage.getItem(CacheKey.LayoutKey) || 'default';
  };

  setLayout = (value: any) => {
    Storage.setItem(CacheKey.LayoutKey, value);
  };
}

export default new Cache();
