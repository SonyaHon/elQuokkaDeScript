package com.quokka_script;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import com.quokka_script.psi.QuokkaTokenType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sonyahon on 23/08/2017.
 */
public class QuokkaBlock extends AbstractBlock {
	private SpacingBuilder spacingBuilder;

	protected QuokkaBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, SpacingBuilder spacingBuilder) {
			super(node, wrap, alignment);
			this.spacingBuilder = spacingBuilder;
	}

	@Override
	protected List<Block> buildChildren() {
		List<Block> blocks = new ArrayList<>();
		ASTNode child = myNode.getFirstChildNode();
		while (child != null) {
			if( child.getElementType() != TokenType.WHITE_SPACE) {
				Block block = new QuokkaBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), spacingBuilder);
				blocks.add(block);
			}
			child = child.getTreeNext();
		}
		return blocks;
	}

	@Nullable
	@Override
	public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
		return spacingBuilder.getSpacing(this, child1, child2);
	}

	@Nullable
	@Override
	public Indent getIndent() {
		if(myNode.getElementType() == QuokkaTypes.META ||
				myNode.getElementType() == QuokkaTypes.DEFINE ||
				myNode.getElementType() == QuokkaTypes.DEDENT ||
				myNode.getElementType() == QuokkaTypes.INDENT ||
				myNode.getElementType() == QuokkaTypes.COMMENT_BEGIN ||
				myNode.getElementType() == QuokkaTypes.COMMENT_END ||
				myNode.getElementType() == QuokkaTypes.END_LAST
				) {
			return Indent.getAbsoluteNoneIndent();
		}
		else if(myNode.getElementType() == QuokkaTypes.QS_OBJECT){
			if(myNode.getTreePrev().getElementType() != QuokkaTypes.DEDENT)
				return Indent.getNormalIndent(true);
			else
				return Indent.getNoneIndent();
		}
		else if(myNode.getElementType() == QuokkaTypes.METHOD) {
			return Indent.getNormalIndent(true);
		}
		else if(myNode.getElementType() == QuokkaTypes.FUNCTION) {
			return Indent.getNormalIndent(true);
		}
		else if(myNode.getElementType() == QuokkaTypes.META_INFO_BLOCK) {
			return Indent.getNormalIndent(true);
		}
		else {
			return Indent.getNoneIndent();
		}
	}

	@Override
	public boolean isLeaf() {
		return myNode.getFirstChildNode() == null;
	}
}
