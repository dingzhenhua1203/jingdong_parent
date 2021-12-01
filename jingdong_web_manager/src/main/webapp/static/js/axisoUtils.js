import Axios from "axios";
import qs from "qs";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

let http = Axios.create({
    headers: {
        "content-type": "application/x-www-form-urlencoded; charset=UTF-8",
    },
});
http.defaults.baseURL = '';
// 添加请求拦截器
http.interceptors.request.use(
    function (config) {
        let token = localStorage.getItem("token");
        if (token) {
            config.headers.token = localStorage.getItem("token");
        }
        config.url = config.baseURL + config.url;
        config.data = qs.stringify(config.data);
        // 在发送请求之前做些什么
        return config;
    },
    function (error) {
        console.log(error);
        // 对请求错误做些什么
        // return Promise.reject(error)
    }
);
// 添加响应拦截器
http.interceptors.response.use(
    response => {
        if (!response) {
            return false;
        }
        if (response.data && response.data.code) {
            if (response.data.code == 201) {
                ElementUI.Message({
                    showClose: true,
                    message: response.data.message,
                    type: "error",
                    offset: 100,
                    duration: 2000,
                });
                return;
            } else if (response.data.code == 401) {
                window.location.href = GLOBAL_CONFIG.baseLogin;
                return;
            }
        }
        // 对响应数据做点什么
        if (response && response.data) {
            return response.data;
        }
        return false;


    },
    function (error) {
        // 对响应错误做点什么
        console.log("超时");
        return;
    }
);

function longRequestHttp(arg, method) {
    let nullPromise = new Promise((resolve, reject) => {
            resolve(false);
        }),
        argType = checkArgModeArg(arg),
        dataKey = method == 'get' ? 'params' : 'data';
    if (!argType) {
        return nullPromise;
    }
    if (argType == 1) { //第一种传参方式
        let options = {
                lock: true,
                text: arg[2] || "处理中，请稍等",
                spinner: "el-icon-loading",
                background: "rgba(255,255,255,0)",
                customClass: "loadingMask",
            },
            loading = ElementUI.Loading.service(options)
        return http({
            url: arg[0] || '',
            [dataKey]: arg[1] || {},
            method: method,
            responseType: 'json',
            timeout: 600000,
        }).then((res) => {
            loading.close();
            return res;
        });
    } else if (argType == 2) { //第二种传参方式
        let options = {
            lock: true,
            text: arg[0].message || "处理中，请稍等",
            spinner: "el-icon-loading",
            background: "rgba(255,255,255,0)",
            customClass: "loadingMask",
        }
        let loading = ElementUI.Loading.service(options);
        return http({
            url: arg[0].url,
            [dataKey]: arg[0].data || {},
            method: method,
            responseType: arg[0].type || 'json',
            timeout: arg[0].timeout || 600000,
        }).then((res) => {
            loading.close();
            return res;
        });
    } else {
        return nullPromise;
    }
}

function requestHttp(arg, method) {
    let nullPromise = new Promise((resolve, reject) => {
            resolve(false);
        }),
        argType = checkArgModeArg(arg),
        dataKey = method == 'get' ? 'params' : 'data';
    if (!argType) {
        return nullPromise;
    }
    if (argType == 1) { //第一种传参方式
        return http({
            url: arg[0] || '',
            [dataKey]: arg[1] || {},
            method: method,
            responseType: 'json',
            timeout: 5000,
        });
    } else if (argType == 2) { //第二种传参方式
        return http({
            url: arg[0].url,
            [dataKey]: arg[0].data || {},
            method: method,
            responseType: arg[0].type || 'json',
            timeout: arg[0].timeout || 5000,
        });
    } else {
        return nullPromise;
    }
}

export default {
    /*
    get post 两种传参方式：url,data={} 或者 {url:'',data:{},type:'',timeout:0}
    longGet longPost 两种传参方式：url,data={},message='' 或者 {url:'',data:{},type:'',timeout:0,message:''}
    */
    get(...arg) {
        return requestHttp(arg, 'get');
    },
    post(...arg) {
        return requestHttp(arg, 'post');
    },
    longGet(...arg) {
        return longRequestHttp(arg, 'get');
    },
    longPost(...arg) {
        return longRequestHttp(arg, 'post');
    },
    //一个并发请求，一次可以请求多个 http请求
    moreHttp(option) {
        let arr = [],
            keys = [];
        for (let key in option) {
            keys.push(key);
            arr.push(option[key]);
        }
        return Axios.all(arr).then(
            Axios.spread(function () {
                let result = {};
                for (let i = 0; i < arguments.length; i++) {
                    let item = arguments[i];
                    if (item) {
                        if (item.data && item.data.data) {
                            result[keys[i]] = item.data.data;
                        } else {
                            result[keys[i]] = item;
                        }
                    }
                }
                return result;
            })
        );
    },
};

function checkArgModeArg(arg) {
    if (isEmptyObject(arg)) {
        return false;
    }
    if (arg.length == 1) {
        //如果只有一个参数：有两种可能，一种是字符串(url)，一种是json
        if (isArrayOrObj(arg[0]) == 'object') {
            //参数为配置型
            return 2;
        }
    }
    return 1;
}

//判断json或者数组是否为空,如果为空返回true
function isEmptyObject(obj) {
    if (!obj) {
        return true;
    }

    let type = isArrayOrObj(obj);
    if (!type) {
        return true;
    }
    if (type == "array") {
        let re = obj.length && obj.length > 0 ? false : true;
        return re;
    }
    for (let key in obj) {
        return false;
    }
    return true;
}

//判断一个变量是数组还是json
function isArrayOrObj(arg) {
    if (typeof arg == "object") {
        //[object Array] [object Object]
        if (Object.prototype.toString.call(arg) == "[object Array]") {
            return "array";
        }
        if (Object.prototype.toString.call(arg) == "[object Object]") {
            return "object";
        }
    }
    return false;
}