var path = require('path');

module.exports = {
    entry: './src/main/resources/js/index.jsx',
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [
                    {
                        loader: "babel-loader"
                    },
                    {
                        loader: 'babel-loader',
                        options: {
                            presets: [
                                "@babel/preset-env",
                                "@babel/preset-react",
                                {
                                    plugins: [
                                        '@babel/plugin-proposal-class-properties'
                                    ]
                                }
                            ]
                        }
                    }]
            }, {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    resolve: {
        extensions: ['.js', '.jsx'],
    }
};