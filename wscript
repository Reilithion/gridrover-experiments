#! /usr/bin/env python
# encoding: utf-8

# GridRover Copyright (C) 2008-2009  Lucas Adam M. Paul
# See LICENSE.TXT for full license text

import os, Options

VERSION='0.0.1'
APPNAME='GridRover'

srcdir = '.'
blddir = '_build_'

def set_options(opt):
	opt.add_option('--native', action='store_true', default=False, help='Build natively with gcj')

def configure(conf):
	print '--native = %s' % (Options.options.native)
	conf.env['GRIDNATIVE'] = Options.options.native

	if conf.env['GRIDNATIVE']:
		conf.check_tool('gcj', tooldir='.')
		if conf.check_java_class('java.util.ArrayList'): conf.fatal('Unable to find JDK.')
		if conf.check_java_class('java.io.FileOutputStream'): conf.fatal('Unable to find JDK.')
	else:
		conf.check_tool('java')
		if conf.check_java_class('java.util.ArrayList'): conf.fatal('Unable to find JDK.')
		if conf.check_java_class('java.io.FileOutputStream'): conf.fatal('Unable to find JDK.')

def build(bld):
	if bld.env['GRIDNATIVE']:
		obj = bld.new_task_gen(features='gcj')
		obj.java_source = '.*java$'
		obj.source_root = 'src'
		obj.target = 'gridrover'
		obj.env.append_value('GCJFLAGS', ['-I', '../src'])
		obj.gcjlinkflags = ['--main=gridrover.GridRover']
		obj.gcjonce = False
	else:
		obj = bld.new_task_gen('java')
		obj.source = '.*java$'
		obj.jarname = 'gridrover.jar'
		obj.source_root = 'src'
		obj.env.append_value('JAVAC', ['-source', '1.5', '-target', '1.5'])
		obj.env['JARCREATE'] = obj.env['JARCREATE'] + 'm'
		build_root = obj.path.find_dir(obj.source_root).abspath(obj.env)
		obj.jaropts = ['../src/Manifest.txt', '-C', build_root, '.']

