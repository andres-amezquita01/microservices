#!/bin/sh

echo "Trying to apply migration"
npx platformatic db migrations apply
echo "Starting Platformatic App..."
npm start
