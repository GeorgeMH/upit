requirejs.config({
    "baseUrl": "scripts/lib",
    shim: {
        'sammy': { deps: ['jquery'], exports: 'Sammy' }
    },
    "paths": {
      "app": "../app"
    }
});

requirejs(["app/upit"]);
