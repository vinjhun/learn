	var data = {
			"fc" : "800",
			"lvl" : "0",
			"seq" : "0",
			"subNode" : [
				{
					"fc" : "851",
					"lvl" : "1",
					"seq" : "0",
					"subNode" : [
						{
							"fc" : "805",
							"lvl" : "2",
							"seq" : "0",
							 
						},
					]
				},
				{
					"fc" : "801",
					"lvl" : "1",
					"seq" : "0",
					"subNode" : [
						{
							"fc" : "805",
							"lvl" : "2",
							"seq" : "0",
							 
						},
						{
							"fc" : "841",
							"lvl" : "2",
							"seq" : "0",
						},
						{
							"fc" : "805",
							"lvl" : "2",
							"seq" : "1",
						}
					]
				}
			]
		};
	
	var _default = {
		
		//settings
		maxLevel : 2,
		subNode : "subNode",
		tfName : "fc",
		tfLvl : "lvl",
			
		//color	
		onHoverColor : "#f9f9f9",
		
		//indent
		indent : "indent",
		
		//mdi
		defaultIcon : "glyphicon",
		expandedIcon : "glyphicon glyphicon-plus",
		collapsedIcon : "glyphicon glyphicon-minus",
		tfEdit : "glyphicon glyphicon-edit pull-right",
		tfAdd : "glyphicon glyphicon-plus-sign pull-right",
		tfDelete : "glyphicon glyphicon-remove-sign pull-right",
		
		//event
		addChildNode : true,
		deleteChildNode : true,
		toggleNode : true,
		
		//ignoreList
		ignoreProperties : [],
	};
	
	//instantiation of tree;
	var TFTree = function(element, options) {
		
		var _that = this,
			_currentSettings = $.extend({}, _default, options),
			$ul = $("<ul>", {"class" : "list-group tftree"});
		
		_that.constructTree($ul, options.data, 0, _currentSettings);
		_that.config($ul, _currentSettings);
		
		return $ul;
	};
	
	//Recursive function only run on object literal
	TFTree.prototype.constructTree = function(ul, data, level, opts) {
		
		if(typeof data === 'undefined' || opts.maxLevel < level)
		{
			return;
		}
		
		var _that = this,
			subNoList = [],
			attributes = {};
		
		Object.keys(data).forEach(function(key){
			
			if([key] == opts.subNode)
			{
				subNoList = data[key];
			}else{
				$.extend(attributes, {
					[key] : data[key],
				});
			}	
		});
		
		if(Object.keys(attributes).length > 0)
		{
			var $li = $('<li>', Object.assign({
				"class" : "list-group-item",
	   		}, attributes));
			  
			$li = constructList($li, attributes[opts.tfLvl], opts);
						
			$(ul).append($li);
		}	
		
		if(Array.isArray(subNoList) && subNoList.length > 0 )
		{
			if(subNoList.length > 0)
			{
				level++;
				
				subNoList.forEach(function(obj){	
					return _that.constructTree(ul, obj, level, opts);
				});
			}
		}
	}
	
	//configure
	TFTree.prototype.config = function(ul, opts){
		
		if(opts.toggleNode !== 'undefined' && opts.toggleNode)
		{
			this.toggleNode(ul,opts);
		}else{
			delete this.toggleNode;
		}
		
		if(opts.addChildNode !== 'undefined' && opts.addChildNode)
		{
			this.addChildNode(ul,opts);
		}else{
			delete this.addChildNode;
		}
		
		if(opts.deleteChildNode !== 'undefiend' && opts.deleteChildNode)
		{
			this.deleteChildNode(ul,opts);
		}else{
			delete this.deleteChildNode;
		}
	}
	
	/**
		Toggle all the li list
	**/
	TFTree.prototype.toggleNode = function(ul, opts) {
			
		$(ul).off('click','i').on('click','i', function(e){
			
			e.preventDefault();
			
			if(!$(this).hasClass(opts.defaultIcon))
				return;
			
			var lvl = $(this).parent().attr(opts.tfLvl),
				isExpanded = $(this).hasClass(opts.collapsedIcon);
			
			if(isExpanded)
			{
				$(this).removeClass(opts.collapsedIcon).addClass(opts.expandedIcon);
				$(this).parent().nextUntil('li[lvl="'+lvl+'"]').find('i').removeClass(opts.collapsedIcon).addClass(opts.expandedIcon);
				$(this).parent().nextUntil('li[lvl="'+lvl+'"]').hide();
			}else{
				$(this).removeClass(opts.expandedIcon).addClass(opts.collapsedIcon);
				$(this).parent().nextUntil('li[lvl="'+lvl+'"]').find('i').removeClass(opts.expandedIcon).addClass(opts.collapsedIcon);
				$(this).parent().nextUntil('li[lvl="'+lvl+'"]').show();
			}
			
		});
	};
	
	//add child node FIXME
	TFTree.prototype.addChildNode = function(ul, opts) {
		
		$(ul).on('click','a', function(e){
			
			e.preventDefault();
			
			if(!$(e.currentTarget).hasClass(opts.tfAdd))
				return;
			
			//add btn begin
			//real situation required to get the parent() and determine the sub product list.
			//require to validate sequence as well
			
			//temp do just add the row. cause it is event handler.
			//it cannot perform 
			
			var $currentTf  = $(e.currentTarget).parent();
			var attributes = {};
			
			$.each($currentTf,function(){
				$.each(this.attributes,function(){
					if(this.specified)
					{
						$.extend(attributes,{
							[this.name] : this.value
						})
					}	
				});
			});
			
			//create a new li FIXME HARDCODED
			var $li = $('<li>', {
				"class" : "list-group-item",
				[opts.tfLvl] : (parseInt(attributes[opts.tfLvl]) + 1),
				[opts.tfName] : "811",
				"seq" : "0",			
   			}),	//fc,seq all require to get after ajaxContent;
				currentLvl = (parseInt(attributes[opts.tfLvl]) + 1);
			
			$li = constructList($li, currentLvl, opts);
			
			//if it is collapse, required to expand
			expandUponAddList($currentTf, $currentTf.attr('lvl'), opts);
			
			console.log($currentTf.nextUntil('li[lvl="'+attributes[opts.tfLvl]+'"]'));
			
			if($currentTf.nextUntil('li[lvl="'+attributes[opts.tfLvl]+'"]').last().length > 0)
			{
				$currentTf.nextUntil('li[lvl="'+attributes[opts.tfLvl]+'"]').last().after($li);
			}else{
				$currentTf.after($li);
			}
		});
	};
	
	//deleteChild Node,
	TFTree.prototype.deleteChildNode = function(ul, opts){
		
		$(ul).on('click','a', function(e){
			
			e.preventDefault();
			
			if(!$(e.currentTarget).hasClass(opts.tfDelete))
				return;
			
			//visually delete,
			//after that require send ajax back to delete in the session
			
			//condition
			//(1). when delete, check whether has child lvl
			//(2). [optional] prompt dialog for confirm deletion,
			//(3). [TODO] remove session object
			//TO REMIND : nextUntil(..) will get all elements that are not same until reach the same one
				
			var $currentTf = $(e.currentTarget).parent(),
				lvl = $currentTf.attr(opts.tfLvl),
				elementToDelete = [],
				childName = [];
			
			elementToDelete.push($currentTf);
			childName.push($currentTf.attr(opts.tfName));
					
			$currentTf.nextUntil('li[lvl="'+$currentTf.attr(opts.tfLvl)+'"]').each(function(key,element){
				
				if($(element).attr(opts.tfLvl) > lvl)
				{
					elementToDelete.push($(element));
					childName.push($(element).attr(opts.tfName));
				}				
			});
			
			console.log(elementToDelete);
			
			var remove = confirm("Are you sure you want to delete " + childName.toString() + ' ?');
			
			if(remove)
			{
				$.each(elementToDelete,function(key,element){
					$(element).remove();
				});
			}
		});
	}
	
	//hoisting
	function constructList($li, level, opts){
		
		//indent control
		for(var i = 0;i < level; i++)
		{
			var $indentDiv = $('<span>', {
					"class" : "indent",
					"style" : "margin-left:2vw",
				});
			$li.append($indentDiv);
		}
		
		
		//append icon
		//edit
		var $aTfEdit = $('<a>',{
			"class" : opts.tfEdit,
			"style" : "margin-left:2vw",
		});
		$li.append($aTfEdit);
		
		//delete
		if(opts.deleteChildNode)
		{
			if(level != 0)
			{
				var $aTfDelete = $('<a>',{
					"class" : opts.tfDelete,
					"style" : "margin-left:1vw",
				});
				
				$li.append($aTfDelete);
			}else{
				
				var $span = $('<span>',{
					"style" : "margin-left:3vw",
					"class" : "pull-right", 
				})
				
				$li.append($span.append('&nbsp;'));
			}
		}	
		
		if(opts.maxLevel > level)
		{
			$li.append($('<i>', {
				"class" : opts.collapsedIcon,
			}));
			
			//add
			if(opts.addChildNode)
			{
				var $aTfAdd = $('<a>',{
					"class" : opts.tfAdd
				});
				$li.append($aTfAdd);
			}
		}else{
			var $indentDiv = $('<span>', {
					"class" : "indent",
					"style" : "margin-left:2vw",
				});
				
			$li.append($indentDiv);
		}
		
		
		$li.append($li.attr(opts.tfName));
		
		return $li;
	}
	
	function expandUponAddList($currentTf, lvl, opts){
		var isExpanded = $currentTf.find('i').hasClass(opts.collapsedIcon);
		
		if(!isExpanded)
		{
			$currentTf.find('i').removeClass(opts.expandedIcon).addClass(opts.collapsedIcon);
			$currentTf.nextUntil('li[lvl="'+lvl+'"]').find('i').removeClass(opts.expandedIcon).addClass(opts.collapsedIcon);
			$currentTf.nextUntil('li[lvl="'+lvl+'"]').show();
		}
	}
	
	var a = new TFTree(null,{
				data : data,
				
			});
	
	$('#glyphicons').append($(a))