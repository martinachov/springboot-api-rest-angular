module.exports = {
    "env": {
        "browser": true,
        "es6": true,
        "cypress/globals": true
    },
    "extends": "plugin:cypress/recommended",
    "globals": {
        "Atomics": "readonly",
        "SharedArrayBuffer": "readonly"
    },
    "parserOptions": {
        "ecmaFeatures": {
            "jsx": true
        },
        "ecmaVersion": 2018
    },
    "plugins": [
    	"cypress"
    ],
    "rules": {
    	"cypress/no-assigning-return-values": "error",
        "cypress/no-unnecessary-waiting": "error",
        "cypress/assertion-before-screenshot": "warn",
        "semi": ["error", "always"],
        "quotes": ["error", "single"]
    }
};