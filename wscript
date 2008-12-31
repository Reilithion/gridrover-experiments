#! /usr/bin/env python
# encoding: utf-8
# Thomas Nagy, 2008 (ita)

# GridRover Copyright (C) 2008  Lucas Adam M. Paul
# See LICENSE for full license text

import os, Options

VERSION='0.0.0'
APPNAME='gcj_test'

srcdir = '.'
blddir = '_build_'

def set_options(opt):
	opt.add_option('--native', action='store_true', default=False, help='Build natively with gcj')

def configure(conf):
	print '--native = %s' % (Options.options.native)
	conf.env['GRIDNATIVE'] = Options.options.native
	#print 'GRIDNATIVE = %s' % (conf.env['GRIDNATIVE'])

	if conf.env['GRIDNATIVE']:
		conf.check_tool('gcj', tooldir='.')
	else:
		conf.check_tool('java')
		#conf.check_java_class('java.io.FileOutputStream')
		#conf.check_java_class('FakeClass')

def build(bld):
	if bld.env['GRIDNATIVE']:
		obj = bld.new_task_gen(features='gcj')
		obj.java_source = '.*java$'
		obj.source_root = 'src'
		obj.target = 'experiment'
		obj.env.append_value('GCJFLAGS', '-I ../src')
		obj.gcjlinkflags = '--main=Experiment'
		obj.gcjonce = False
	else:
		obj = bld.new_task_gen('java')
		obj.source = '.*java$'
		obj.jarname = 'experiment.jar'
		obj.source_root = 'src'
		obj.env.append_value('JAVAC', '-5')
		obj.jaropts = '-m ../Manifest.txt '
		build_root = obj.path.find_dir(obj.source_root).abspath(obj.env)
		obj.jaropts += '-C %s %s' % (build_root, '.')
