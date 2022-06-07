#!/bin/bash
set -e

umask 0002
if [ "$1" = 'rpc_module' ]; then
  exec python rpc_victoria.py
fi

exec "$@"
