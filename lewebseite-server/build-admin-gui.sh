#!/bin/bash
pushd
cd ../admin-ui
npm install
npm run build
popd

cp -r ../admin-ui/dist/ 
