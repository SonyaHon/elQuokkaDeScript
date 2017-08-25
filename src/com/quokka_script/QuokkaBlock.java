package com.quokka_script;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
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
				if(child.getElementType() != QuokkaTypes.COMMENT) {
					Block block = new QuokkaBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(), spacingBuilder);
					blocks.add(block);
				}
				else {
					Block block = new QuokkaBlock(child, Wrap.createWrap(WrapType.CHOP_DOWN_IF_LONG, false), Alignment.createAlignment(), spacingBuilder);
					blocks.add(block);
				}
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

	@Override
	public boolean isLeaf() {
		return myNode.getFirstChildNode() == null;
	}
}
